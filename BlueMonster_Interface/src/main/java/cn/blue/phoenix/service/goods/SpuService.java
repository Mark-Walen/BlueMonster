package cn.blue.phoenix.service.goods;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.goods.Goods;
import cn.blue.phoenix.pojo.goods.Spu;

import java.util.List;
import java.util.Map;

public interface SpuService {
    List<Spu> findAll();

    PageResult<Spu> findPage(Integer page, Integer size);

    List<Spu> findList(Map<String, Object> searchMap);

    PageResult<Spu> findPage(Map<String, Object> searchMap, Integer page, Integer size);

    Spu findById(Integer id);

    void add(Spu spu);

    void update(Spu spu);

    void delete(Integer id);

    void saveGoods(Goods goods);

    Goods findGoodsById(String id);

    void audit(String id, String status, String message);

    void pull(String id);

    void put(String id);

    int putMany(String[] ids);
}
