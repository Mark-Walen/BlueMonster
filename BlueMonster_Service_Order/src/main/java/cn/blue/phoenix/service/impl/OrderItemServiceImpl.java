package cn.blue.phoenix.service.impl;

import cn.blue.phoenix.dao.OrderItemMapper;
import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.order.OrderItem;
import cn.blue.phoenix.service.order.OrderItemService;
import cn.blue.phoenix.utils.PageHelperUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @author : BlueVincent
 * @version V1.0
 * @Project: BlueMonster
 * @Package cn.blue.phoenix.service.impl
 * @date Date : 2022年01月12日 23:06
 */
@DubboService(interfaceClass = OrderItemService.class)
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;
    private final PageHelperUtils<OrderItem> pageHelperUtils = new PageHelperUtils<>("id", "spuId", "skuId", "orderId", "name", "image", "isReturn");

    @Override
    public List<OrderItem> findAll() {
        return orderItemMapper.selectAll();
    }

    @Override
    public PageResult<OrderItem> findPage(Integer page, Integer size) {
        PageHelperUtils.Result<OrderItem> orderItemResult = pageHelperUtils.pageHelperUtils(OrderItemMapper.class, null, page, size, "selectAll");
        return new PageResult<>(orderItemResult.getTotal(), orderItemResult.getList());
    }

    @Override
    public List<OrderItem> findList(Map<String, Object> searchMap) {
        Example example = pageHelperUtils.createExample(searchMap, OrderItem.class);
        return orderItemMapper.selectByExample(example);
    }

    @Override
    public PageResult<OrderItem> findPage(Map<String, Object> searchMap, Integer page, Integer size) {
        Example example = pageHelperUtils.createExample(searchMap, OrderItem.class);
        PageHelperUtils.Result<OrderItem> orderItemResult = pageHelperUtils.pageHelperUtils(OrderItemMapper.class, example, page, size, "selectByExample");
        return new PageResult<>(orderItemResult.getTotal(), orderItemResult.getList());
    }

    @Override
    public OrderItem findById(String id) {
        return orderItemMapper.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public void add(OrderItem orderItem) {
        orderItemMapper.insert(orderItem);
    }

    @Transactional
    @Override
    public void update(OrderItem orderItem) {
        orderItemMapper.updateByPrimaryKeySelective(orderItem);
    }

    @Override
    public void delete(String id) {
        orderItemMapper.deleteByPrimaryKey(id);
    }
}
