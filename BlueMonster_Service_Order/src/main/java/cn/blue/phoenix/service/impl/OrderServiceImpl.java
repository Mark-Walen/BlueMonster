package cn.blue.phoenix.service.impl;

import cn.blue.phoenix.dao.OrderConfigMapper;
import cn.blue.phoenix.dao.OrderItemMapper;
import cn.blue.phoenix.dao.OrderLogMapper;
import cn.blue.phoenix.dao.OrderMapper;
import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.order.*;
import cn.blue.phoenix.service.order.OrderService;
import cn.blue.phoenix.utils.IdWorker;
import cn.blue.phoenix.utils.PageHelperUtils;
import cn.blue.phoenix.utils.SpringContextUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>BlueMonster</p>
 * <p>cn.blue.phoenix.service.impl</p>
 * <p>Date : 2022年01月12日 22:03</p>
 *
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

    @Transactional
    @Override
    public void batchSend(List<Order> orders) {
        // 判断运单号和物流公司是否为空
        for (Order order : orders) {
            if (order.getShippingCode() == null || order.getShippingName() == null) {
                throw new RuntimeException("请选择快递公司和填写快递单号");
            }
        }

        for (Order order : orders) {
            order.setOrderStatus("3");          // 订单状态 已发货
            order.setConsignStatus("2");        // 发货状态 已发货
            order.setConsignTime(new Date());   // 发货时间
            orderMapper.updateByPrimaryKeySelective(order);
            // 记录订单日志
        }
    }

    @Override
    public void orderTimeoutLogic() {
        // 订单超时未付款 自动关闭
        //查询超时时间
        OrderConfigMapper orderConfigMapper = SpringContextUtils.getBean(OrderConfigMapper.class);
        OrderLogMapper orderLogMapper = SpringContextUtils.getBean(OrderLogMapper.class);

        OrderConfig orderConfig = orderConfigMapper.selectByPrimaryKey(1);
        Integer orderTimeout = orderConfig.getOrderTimeout();   // 获得超时的时间点

        // 设置查询条件
        Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();       // 创建时间小于超时的
        criteria.andEqualTo("isDelete", "0");       // 未删除的
        criteria.andEqualTo("orderStatus", "0");    // 未付款的

        List<Order> orderList = orderMapper.selectByExample(example);

        for (Order order : orderList) {
            // 记录订单变动日志
            OrderLog orderLog = new OrderLog();
            orderLog.setOperater("system");
            orderLog.setOperateTime(new Date());
            orderLog.setOrderStatus("4");
            orderLog.setPayStatus(order.getPayStatus());    //
            orderLog.setConsignStatus(order.getConsignStatus());
            orderLog.setRemarks("订单超时，系统自动关闭");
            orderLog.setOrderId(order.getId());
            orderLogMapper.insert(orderLog);

            // 更改订单状态
            order.setConsignStatus("4");
            order.setCloseTime(new Date());
            orderMapper.updateByPrimaryKeySelective(order);
        }
    }
}
