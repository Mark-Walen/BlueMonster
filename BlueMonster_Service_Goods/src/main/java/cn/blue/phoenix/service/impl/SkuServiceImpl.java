package cn.blue.phoenix.service.impl;

import cn.blue.phoenix.dao.SkuMapper;
import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.goods.Sku;
import cn.blue.phoenix.service.goods.SkuService;
import cn.blue.phoenix.utils.PageHelperUtils;
import com.github.pagehelper.PageHelper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@DubboService(interfaceClass = SkuService.class)
public class SkuServiceImpl implements SkuService {

    @Autowired
    private SkuMapper skuMapper;

    // TODO 记得添加模糊查询
    private final PageHelperUtils<Sku> pageUtils = new PageHelperUtils<>();

    @Override
    public List<Sku> findAll() {
        return skuMapper.selectAll();
    }

    @Override
    public PageResult<Sku> findPage(Integer page, Integer size) {
        PageHelperUtils.Result<Sku> skuResult = pageUtils.pageHelperUtils(SkuMapper.class, null, page, size, "selectAll");
        return new PageResult<>(skuResult.getTotal(), skuResult.getList());
    }

    @Override
    public List<Sku> findList(Map<String, Object> searchMap) {
        Example example = pageUtils.createExample(searchMap, Sku.class);
        return skuMapper.selectByExample(example);
    }

    @Override
    public PageResult<Sku> findPage(Map<String, Object> searchMap, Integer page, Integer size) {
        Example example = pageUtils.createExample(searchMap, Sku.class);
        PageHelperUtils.Result<Sku> skuResult = pageUtils.pageHelperUtils(SkuMapper.class, example, page, size, "selectByExample");
        return new PageResult<>(skuResult.getTotal(), skuResult.getList());
    }

    @Override
    public Sku findById(String id) {
        return skuMapper.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public void add(Sku sku) {
        skuMapper.insert(sku);
    }

    @Override
    public void update(Sku sku) {
        skuMapper.updateByPrimaryKeySelective(sku);
    }

    @Transactional
    @Override
    public void delete(String id) {
        skuMapper.deleteByPrimaryKey(id);
    }
}
