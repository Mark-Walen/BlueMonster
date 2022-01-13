package cn.blue.phoenix.service.impl;

import cn.blue.phoenix.dao.BrandMapper;
import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.goods.Brand;
import cn.blue.phoenix.service.goods.BrandService;
import cn.blue.phoenix.utils.PageHelperUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@DubboService(interfaceClass = BrandService.class)
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;

    private final PageHelperUtils<Brand> pageUtils = new PageHelperUtils<>();

    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    @Override
    public PageResult<Brand> findPage(Integer page, Integer size) {
        /*PageHelper.startPage(page, size);
        List<Brand> list = brandMapper.selectAll();

        // 封装查询结果
        PageInfo<Brand> pageInfo = new PageInfo<>(list);

        // 获取记录总数
        long total = pageInfo.getTotal();
        // 获取当前页数列表
        List<Brand> brandList = pageInfo.getList();
        return new PageResult<>(total, brandList);*/
        PageHelperUtils.Result<Brand> brandResult = pageUtils.pageHelperUtils(BrandMapper.class, null, page, size, "selectAll");
        return new PageResult<>(brandResult.getTotal(), brandResult.getList());
    }

    @Override
    public List<Brand> findList(Map<String, Object> searchMap) {
        Example example = pageUtils.createExample(searchMap, Brand.class);
        return brandMapper.selectByExample(example);
    }

    @Override
    public PageResult<Brand> findPage(Map<String, Object> searchMap, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        Example example = pageUtils.createExample(searchMap, Brand.class);
        List<Brand> list = brandMapper.selectByExample(example);

        PageInfo<Brand> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        List<Brand> brandList = pageInfo.getList();
        return new PageResult<>(total, brandList);
    }

    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public void add(Brand brand) {
        brandMapper.insert(brand);
    }

    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }
}
