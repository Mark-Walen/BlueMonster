package cn.blue.phoenix.service.impl;

import cn.blue.phoenix.dao.CategoryBrandMapper;
import cn.blue.phoenix.dao.CategoryMapper;
import cn.blue.phoenix.dao.SkuMapper;
import cn.blue.phoenix.dao.SpuMapper;
import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.goods.*;
import cn.blue.phoenix.service.goods.SpuService;
import cn.blue.phoenix.utils.IdWorker;
import cn.blue.phoenix.utils.PageHelperUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@DubboService(interfaceClass = SpuService.class)
public class SpuServiceImpl implements SpuService {
    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryBrandMapper categoryBrandMapper;

    // TODO 记得添加模糊查询
    private final PageHelperUtils<Spu> pageUtils = new PageHelperUtils<>();

    @Override
    public List<Spu> findAll() {
        return spuMapper.selectAll();
    }

    @Override
    public PageResult<Spu> findPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Spu> list = spuMapper.selectAll();
        return new PageResult<>(pageUtils.getTotal(),pageUtils.pageHelperUtils(list, page, size));
    }

    @Override
    public List<Spu> findList(Map<String, Object> searchMap) {
        Example example = pageUtils.createExample(searchMap, Spu.class);
        return spuMapper.selectByExample(example);
    }

    @Override
    public PageResult<Spu> findPage(Map<String, Object> searchMap, Integer page, Integer size) {
        Example example = pageUtils.createExample(searchMap, Spu.class);
        PageHelper.startPage(page, size);
        List<Spu> list = spuMapper.selectByExample(example);

        return new PageResult<>(pageUtils.getTotal(),pageUtils.pageHelperUtils(list, page, size));
    }

    /**
     *
     * @return 回收站中的所有商品
     */
    @Override
    public PageResult<Goods> findRecoveryGoods(Integer page, Integer size) {
        // 获取回收站中商品 spu
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDelete", '1');

        PageHelper.startPage(page, size);
        List<Spu> spuList = spuMapper.selectByExample(example);

        // 组装 Goods
        List<Goods> goodsList = new ArrayList<>();
        for (Spu spu: spuList) {
            goodsList.add(this.findGoodsById(spu.getId()));
        }
        PageHelperUtils<Goods> goodsPageHelperUtils = new PageHelperUtils<>();
        goodsList = goodsPageHelperUtils.pageHelperUtils(goodsList, page, size);
        return new PageResult<>(goodsPageHelperUtils.getTotal(), goodsList);
    }

    @Override
    public void deleteGoods(String id) {
        // 删除 sku
        if (id.equals("")) return;
        Example skuExample = new Example(Sku.class);
        Example.Criteria skuExampleCriteria = skuExample.createCriteria();
        skuExampleCriteria.andEqualTo("spuId", id);

        skuMapper.deleteByExample(skuExample);

        // 删除 spu;
        spuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Spu findById(String id) {
        return spuMapper.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public void add(Spu spu) {
        spuMapper.insert(spu);
    }

    @Override
    public void update(Spu spu) {
        spuMapper.updateByPrimaryKeySelective(spu);
    }

    @Transactional
    @Override
    public void delete(String id) {
        spuMapper.deleteByPrimaryKey(id);
    }

    /**
     * 由于添加商品后修改商品的代码有许多重复，因此进行一个整合
     * @param goods 商品信息
     */
    @Transactional
    @Override
    public void saveGoods(Goods goods) {

        // 保存一个 Spu
        Spu spu = goods.getSpu();

        // 如果是添加，则 Spu 对象一定是没有 id 的
        if (spu.getId() == null) {
            spu.setId(idWorker.nextId() + "");
            spuMapper.insert(spu);
        } else {
            // 根据 spuId 删除 sku 列表，然后进行添加
            Example example = new Example(Sku.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("spuId", spu.getId());
            skuMapper.deleteByExample(example);

            // 执行 Spu 的修改
            spuMapper.updateByPrimaryKeySelective(spu);
        }

        // 保存 Sku 列表信息
        List<Sku> skuList = goods.getSkuList();
        Date date = new Date();

        // 获取 Category 对象，用于查询分类的名称
        Category category = categoryMapper.selectByPrimaryKey(spu.getCategory3Id());

        for (Sku sku:skuList) {

            // 如果是添加，则 Sku 对象一定是没有 id 的
            if (sku.getId() == null) {
                sku.setId(idWorker.nextId() + "");
                sku.setCreateTime(date);                    // 创建时间
            }

            sku.setSpuId(spu.getId());

            // 不启用规格的 Sku 处理
            if (sku.getSpec() == null || "".equals(sku.getSpec())) {
                sku.setSpec("{}");
            }

            // Sku 名称 = Spu 名称 + 规格值列表
            StringBuilder name = new StringBuilder(spu.getName());

            Map<String, String> specMap = JSON.parseObject(sku.getSpec(), Map.class);
            for (String value: specMap.values()) {
                name.append(value);
            }

            sku.setName(name.toString());               // Sku 名称
            sku.setUpdateTime(date);                    // 修改时间
            sku.setCategoryId(spu.getCategory3Id());    // 分类 id
            sku.setCategoryName(category.getName());    // 分类名称
            sku.setCommentNum(0);                       // 销售数量
            sku.setSaleNum(0);                          // 销售数量

            skuMapper.insert(sku);
        }

        // 建立分类与品牌的关联，从 Spu 中获取主键 id
        CategoryBrand categoryBrand = new CategoryBrand();
        categoryBrand.setCategoryId(spu.getCategory3Id());
        categoryBrand.setBrandId(spu.getBrandId());

        // 处理主键冲突
        int count = categoryBrandMapper.selectCount(categoryBrand);
        if (count == 0) categoryBrandMapper.insert(categoryBrand);
    }

    @Override
    public Goods findGoodsById(String id) {
        // 根据 id 查询 Spu
        Spu spu = spuMapper.selectByPrimaryKey(id);

        // 查询 sku 列表
        Example example = new Example(Sku.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("spuId", id);
        List<Sku> skuList = skuMapper.selectByExample(example);

        // 封装为组合实体类
        Goods goods = new Goods();
        goods.setSpu(spu);
        goods.setSkuList(skuList);
        return goods;
    }

    /**
     *
     * @param id spuId
     * @param status 商品审核状态
     * @param message 商品审核信息
     */
    @Transactional
    @Override
    public void audit(String id, String status, String message) {
        Spu spu = new Spu();
        spu.setId(id);
        spu.setStatus(status);

        // 审核通过
        if ("1".equals(status)) {
            spu.setIsMarketable(status);    // 自动上架
        }
        spuMapper.updateByPrimaryKeySelective(spu);

        // TODO 记录商品审核记录
        // TODO 记录商品日志
    }

    /**
     * 下架商品
     * @param id SpuId
     */
    @Transactional
    @Override
    public void pull(String id) {
        Spu spu = spuMapper.selectByPrimaryKey(id);
        spu.setIsMarketable("0");
        spuMapper.updateByPrimaryKeySelective(spu);
    }

    /**
     * 上架商品
     * @param id spuId
     */
    @Transactional
    @Override
    public void put(String id) {
        Spu spu = spuMapper.selectByPrimaryKey(id);
        if (!"1".equals(spu.getStatus())) {
            throw new RuntimeException("此商品未通过审核！！！");
        }
        spu.setIsMarketable("1");
        spuMapper.updateByPrimaryKeySelective(spu);

        // TODO 记录商品日志
    }

    /**
     * 根据 id 批量上架
     * @param ids spuId 列表
     */
    @Override
    public int putMany(String[] ids) {
        Spu spu = new Spu();
        spu.setIsMarketable("1");

        // 批量修改
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", Arrays.asList(ids));
        criteria.andEqualTo("isMarketable", "0");   // 下架
        criteria.andEqualTo("status", "1");         // 审核通过
        criteria.andEqualTo("isDelete", "0");       // 未被删除的

        return spuMapper.updateByExampleSelective(spu, example);
    }
}
