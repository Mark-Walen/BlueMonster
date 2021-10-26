package cn.blue.phoenix.service.goods;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.goods.Spec;

import java.util.List;
import java.util.Map;

public interface SpecService {
    List<Spec> findAll();

    PageResult<Spec> findPage(Integer page, Integer size);

    List<Spec> findList(Map<String, Object> searchMap);

    PageResult<Spec> findPage(Map<String, Object> searchMap, Integer page, Integer size);

    Spec findById(Integer id);

    void add(Spec Spec);

    void update(Spec Spec);

    void delete(Integer id);
}
