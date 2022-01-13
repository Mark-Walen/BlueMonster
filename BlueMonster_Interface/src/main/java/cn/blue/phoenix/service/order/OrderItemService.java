package cn.blue.phoenix.service.order;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.order.OrderItem;

import java.util.*;

/**
 * orderItem业务逻辑层
 */
public interface OrderItemService {

    List<OrderItem> findAll();

    PageResult<OrderItem> findPage(Integer page, Integer size);

    List<OrderItem> findList(Map<String, Object> searchMap);

    PageResult<OrderItem> findPage(Map<String, Object> searchMap, Integer page, Integer size);

    OrderItem findById(String id);

    void add(OrderItem orderItem);

    void update(OrderItem orderItem);

    void delete(String id);
}
