package cn.blue.phoenix.service.order;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.order.OrderConfig;

import java.util.*;

/**
 * orderConfig业务逻辑层
 */
public interface OrderConfigService {


    List<OrderConfig> findAll();


    PageResult<OrderConfig> findPage(Integer page, Integer size);


    List<OrderConfig> findList(Map<String,Object> searchMap);


    PageResult<OrderConfig> findPage(Map<String,Object> searchMap,Integer page, Integer size);


    OrderConfig findById(Integer id);

    void add(OrderConfig orderConfig);


    void update(OrderConfig orderConfig);


    void delete(Integer id);

}
