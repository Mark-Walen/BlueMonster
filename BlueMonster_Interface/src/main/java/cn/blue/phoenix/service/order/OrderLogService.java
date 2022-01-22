package cn.blue.phoenix.service.order;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.order.OrderLog;

import java.util.*;

/**
 * orderLog业务逻辑层
 */
public interface OrderLogService {

    List<OrderLog> findAll();

    PageResult<OrderLog> findPage(Integer page, Integer size);

    List<OrderLog> findList(Map<String, Object> searchMap);

    PageResult<OrderLog> findPage(Map<String, Object> searchMap, Integer page, Integer size);

    OrderLog findById(String id);

    void add(OrderLog orderLog);

    void update(OrderLog orderLog);

    void delete(String id);

}
