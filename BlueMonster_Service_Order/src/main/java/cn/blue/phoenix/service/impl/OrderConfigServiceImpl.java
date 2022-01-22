package cn.blue.phoenix.service.impl;

import cn.blue.phoenix.dao.OrderConfigMapper;
import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.order.OrderConfig;
import cn.blue.phoenix.service.order.OrderConfigService;
import cn.blue.phoenix.utils.PageHelperUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@DubboService
public class OrderConfigServiceImpl implements OrderConfigService {

    @Autowired
    private OrderConfigMapper orderConfigMapper;

    private final PageHelperUtils<OrderConfig> pageHelperUtils = new PageHelperUtils<>("id",
            "payType", "shippingName", "shippingCode", "username", "buyerMessage", "buyerRate",
            "receiverContact", "receiverMobile", "receiverAddress", "sourceType", "transactionId",
            "orderStatus", "payStatus", "consignStatus", "isDelete");

    @Override
    public List<OrderConfig> findAll() {
        return orderConfigMapper.selectAll();
    }

    @Override
    public PageResult<OrderConfig> findPage(Integer page, Integer size) {
        PageHelperUtils.Result<OrderConfig> orderResult = pageHelperUtils.pageHelperUtils(OrderConfigMapper.class, null, page, size, "selectAll");
        return new PageResult<>(orderResult.getTotal(), orderResult.getList());
    }

    @Override
    public List<OrderConfig> findList(Map<String, Object> searchMap) {
        return null;
    }

    @Override
    public PageResult<OrderConfig> findPage(Map<String, Object> searchMap, Integer page, Integer size) {
        Example example = pageHelperUtils.createExample(searchMap, OrderConfig.class);
        PageHelperUtils.Result<OrderConfig> orderResult = pageHelperUtils.pageHelperUtils(OrderConfigMapper.class, example, page, size, "selectAll");
        return new PageResult<>(orderResult.getTotal(), orderResult.getList());
    }

    @Override
    public OrderConfig findById(Integer id) {
        return orderConfigMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(OrderConfig orderConfig) {
        orderConfigMapper.insert(orderConfig);
    }

    @Override
    public void update(OrderConfig orderConfig) {
        orderConfigMapper.updateByPrimaryKeySelective(orderConfig);
    }

    @Override
    public void delete(Integer id) {
        orderConfigMapper.deleteByPrimaryKey(id);
    }
}
