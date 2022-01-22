package cn.blue.phoenix.service.impl;
import cn.blue.phoenix.dao.OrderLogMapper;
import cn.blue.phoenix.dao.OrderMapper;
import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.order.Order;
import cn.blue.phoenix.pojo.order.OrderLog;
import cn.blue.phoenix.service.order.OrderLogService;
import cn.blue.phoenix.utils.PageHelperUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@DubboService
public class OrderLogServiceImpl implements OrderLogService {

    @Autowired
    private OrderLogMapper orderLogMapper;

    private final PageHelperUtils<OrderLog> pageHelperUtils = new PageHelperUtils<>("id", "operater",
            "orderId",
            "orderStatus",
            "payStatus",
            "consignStatus",
            "remarks");

    /**
     * 返回全部记录
     * @return
     */
    public List<OrderLog> findAll() {
        return orderLogMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<OrderLog> findPage(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        PageHelperUtils.Result<OrderLog> orderLogResult = pageHelperUtils.pageHelperUtils(OrderLogMapper.class, null, page, size, "selectAll");
        return new PageResult<>(orderLogResult.getTotal(), orderLogResult.getList());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<OrderLog> findList(Map<String, Object> searchMap) {
        Example example = pageHelperUtils.createExample(searchMap, Order.class);
        return orderLogMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<OrderLog> findPage(Map<String, Object> searchMap, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        Example example = pageHelperUtils.createExample(searchMap, OrderLog.class);
        PageHelperUtils.Result<OrderLog> orderLogResult = pageHelperUtils.pageHelperUtils(OrderLogMapper.class, example, page, size, "selectByExample");
        return new PageResult<>(orderLogResult.getTotal(), orderLogResult.getList());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public OrderLog findById(String id) {
        return orderLogMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param orderLog
     */
    public void add(OrderLog orderLog) {
        orderLogMapper.insert(orderLog);
    }

    /**
     * 修改
     * @param orderLog
     */
    public void update(OrderLog orderLog) {
        orderLogMapper.updateByPrimaryKeySelective(orderLog);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(String id) {
        orderLogMapper.deleteByPrimaryKey(id);
    }

}
