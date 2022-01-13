package cn.blue.phoenix.service.order;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.order.Order;
import cn.blue.phoenix.pojo.order.OrderItem;
import cn.blue.phoenix.pojo.order.OrderItemDetails;

import java.util.List;
import java.util.Map;

/**
 * @author : BlueVincent
 * @version V1.0
 * @Project: BlueMonster
 * @Package cn.blue.phoenix.service.order
 * @date Date : 2022年01月12日 21:57
 */
public interface OrderService {
    List<Order> findAll();

    PageResult<Order> findPage(Integer page, Integer size);

    List<Order> findList(Map<String, Object> searchMap);

    OrderItemDetails findOrderDetailsById(String id);

    PageResult<Order> findPage(Map<String, Object> searchMap, int page, int size);

    Order findById(String id);

    void add(Order order);

    void update(Order order);

    void delete(String id);
}
