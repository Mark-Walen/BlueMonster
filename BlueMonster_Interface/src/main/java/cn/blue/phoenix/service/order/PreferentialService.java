package cn.blue.phoenix.service.order;
import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.order.Preferential;

import java.util.*;

/**
 * preferential业务逻辑层
 */
public interface PreferentialService {


    List<Preferential> findAll();


    PageResult<Preferential> findPage(Integer page, Integer size);


    List<Preferential> findList(Map<String,Object> searchMap);


    PageResult<Preferential> findPage(Map<String,Object> searchMap,Integer page, Integer size);


    Preferential findById(Integer id);

    void add(Preferential preferential);


    void update(Preferential preferential);


    void delete(Integer id);

}
