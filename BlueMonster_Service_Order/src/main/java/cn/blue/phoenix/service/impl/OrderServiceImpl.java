package cn.blue.phoenix.service.impl;

import cn.blue.phoenix.dao.OrderItemMapper;
import cn.blue.phoenix.dao.OrderMapper;
import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.order.Order;
import cn.blue.phoenix.pojo.order.OrderItem;
import cn.blue.phoenix.pojo.order.OrderItemDetails;
import cn.blue.phoenix.service.order.OrderService;
import cn.blue.phoenix.utils.IdWorker;
import cn.blue.phoenix.utils.PageHelperUtils;
import cn.blue.phoenix.utils.SpringContextUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 *
 * <p>BlueMonster</p>
 * <p>cn.blue.phoenix.service.impl</p>
 * <p>Date : 2022年01月12日 22:03</p>
 * @author : BlueVincent
 * @version V1.0
 */
@DubboService(interfaceClass = OrderService.class)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private IdWorker idWorker;

    private final PageHelperUtils<Order> pageHelperUtils = new PageHelperUtils<>("id",
            "payType", "shippingName", "shippingCode", "username", "buyerMessage", "buyerRate",
            "receiverContact", "receiverMobile", "receiverAddress", "sourceType", "transactionId",
            "orderStatus", "payStatus", "consignStatus", "isDelete");

    @Override
    public List<Order> findAll() {
        return orderMapper.selectAll();
    }

    @Override
    public PageResult<Order> findPage(Integer page, Integer size) {
        PageHelperUtils.Result<Order> orderResult = pageHelperUtils.pageHelperUtils(OrderMapper.class, null, page, size, "selectAll");
        return new PageResult<>(orderResult.getTotal(), orderResult.getList());
    }

    @Override
    public List<Order> findList(Map<String, Object> searchMap) {
        Example example = pageHelperUtils.createExample(searchMap, Order.class);
        return orderMapper.selectByExample(example);
    }

    @Override
    public OrderItemDetails findOrderDetailsById(String id) {
        Order order = orderMapper.selectByPrimaryKey(id);

        Example example = new Example(OrderItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId", id);

        OrderItemMapper orderItemMapper = SpringContextUtils.getBean(OrderItemMapper.class);
        List<OrderItem> orderItems = orderItemMapper.selectByExample(example);

        OrderItemDetails orderItemDetails = new OrderItemDetails();
        orderItemDetails.setOrder(order);
        orderItemDetails.setOrderItemList(orderItems);
        return orderItemDetails;
    }

    @Override
    public PageResult<Order> findPage(Map<String, Object> searchMap, int page, int size) {
        Example example = pageHelperUtils.createExample(searchMap, Order.class);
        PageHelperUtils.Result<Order> orderResult = pageHelperUtils.pageHelperUtils(OrderMapper.class, example, page, size, "selectByExample");
        return new PageResult<>(orderResult.getTotal(), orderResult.getList());
    }

    @Override
    public Order findById(String id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public void add(Order order) {
        orderMapper.insert(order);
    }

    @Transactional
    @Override
    public void update(Order order) {
        orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public void delete(String id) {
        orderMapper.deleteByPrimaryKey(id);
    }
}
