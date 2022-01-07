package cn.blue.phoenix.service.impl;

import cn.blue.phoenix.dao.CategoryMapper;
import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.goods.Category;
import cn.blue.phoenix.service.goods.CategoryService;
import cn.blue.phoenix.utils.PageHelperUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DubboService(interfaceClass = CategoryService.class)
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    private final PageHelperUtils<Category> pageUtils = new PageHelperUtils<>("name", "isShow", "isMenu");

    @Override
    public List<Category> findAll() {
        return categoryMapper.selectAll();
    }

    @Override
    public PageResult<Category> findPage(Integer page, Integer size) {
        return new PageResult<>(pageUtils.getTotal(), pageUtils.pageHelperUtils(page, size, CategoryMapper.class, "selectAll"));
    }

    @Override
    public List<Category> findList(Map<String, Object> searchMap) {
        Example example = pageUtils.createExample(searchMap, Category.class);
        return categoryMapper.selectByExample(example);
    }

    @Override
    public PageResult<Category> findPage(Map<String, Object> searchMap, Integer page, Integer size) {
        Example example = pageUtils.createExample(searchMap, Category.class);

        PageHelper.startPage(page, size);
        List<Category> list = categoryMapper.selectByExample(example);

        return new PageResult<>(pageUtils.getTotal(), pageUtils.pageHelperUtils(list, page, size));
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public void add(Category category) {
        categoryMapper.insert(category);
    }

    @Override
    public void update(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        // 判断是否存在下级分类
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId", id);
        int count = categoryMapper.selectCountByExample(example);

        if (count > 0) {
            throw new RuntimeException("存在下级分类不能删除");
        }
        categoryMapper.deleteByPrimaryKey(id);
    }
}
