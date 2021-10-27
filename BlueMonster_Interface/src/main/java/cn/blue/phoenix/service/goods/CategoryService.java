package cn.blue.phoenix.service.goods;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.goods.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    List<Category> findAll();

    PageResult<Category> findPage(Integer page, Integer size);

    List<Category> findList(Map<String, Object> searchMap);

    PageResult<Category> findPage(Map<String, Object> searchMap, Integer page, Integer size);

    Category findById(Integer id);

    void add(Category brand);

    void update(Category brand);

    void delete(Integer id);
}
