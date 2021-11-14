package cn.blue.phoenix.service.goods;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.goods.Sku;

import java.util.List;
import java.util.Map;

public interface SkuService {
    List<Sku> findAll();

    PageResult<Sku> findPage(Integer page, Integer size);

    List<Sku> findList(Map<String, Object> searchMap);

    PageResult<Sku> findPage(Map<String, Object> searchMap, Integer page, Integer size);

    Sku findById(Integer id);

    void add(Sku sku);

    void update(Sku sku);

    void delete(Integer id);
}
