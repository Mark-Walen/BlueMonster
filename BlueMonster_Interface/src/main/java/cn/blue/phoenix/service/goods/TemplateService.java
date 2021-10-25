package cn.blue.phoenix.service.goods;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.goods.Template;

import java.util.List;
import java.util.Map;

/**
 * template业务逻辑层
 */
public interface TemplateService {
    List<Template> findAll();

    PageResult<Template> findPage(Integer page, Integer size);

    List<Template> findList(Map<String, Object> searchMap);

    PageResult<Template> findPage(Map<String, Object> searchMap, Integer page, Integer size);

    Template findById(Integer id);

    void add(Template template);

    void update(Template template);

    void delete(Integer id);
}
