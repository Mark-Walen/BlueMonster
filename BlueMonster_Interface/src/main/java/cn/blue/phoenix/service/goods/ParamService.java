package cn.blue.phoenix.service.goods;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.goods.Param;

import java.util.List;
import java.util.Map;

public interface ParamService {
    List<Param> findAll();

    PageResult<Param> findPage(Integer page, Integer size);

    List<Param> findList(Map<String, Object> searchMap);

    PageResult<Param> findPage(Map<String, Object> searchMap, Integer page, Integer size);

    Param findById(Integer id);

    void add(Param template);

    void update(Param template);

    void delete(Integer id);
}
