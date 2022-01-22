package cn.blue.phoenix.service.impl;

import cn.blue.phoenix.dao.OrderItemMapper;
import cn.blue.phoenix.dao.ReturnOrderItemMapper;
import cn.blue.phoenix.dao.ReturnOrderMapper;
import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.order.OrderItem;
import cn.blue.phoenix.pojo.order.ReturnOrder;
import cn.blue.phoenix.pojo.order.ReturnOrderItem;
import cn.blue.phoenix.service.order.ReturnOrderService;
import cn.blue.phoenix.utils.PageHelperUtils;
import cn.blue.phoenix.utils.SpringContextUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

@DubboService(interfaceClass = ReturnOrderService.class)
public class ReturnOrderServiceImpl implements ReturnOrderService {

    @Autowired
    private ReturnOrderMapper returnOrderMapper;

    PageHelperUtils<ReturnOrder> pageHelperUtils = new PageHelperUtils<>("userAccount", "linkman", "linkmanMobile", "type", "isReturnFreight", "status", "evidence", "description", "remark");

    /**
     * 返回全部记录
     * @return
     */
    @Override
    public List<ReturnOrder> findAll() {
        return returnOrderMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    @Override
    public PageResult<ReturnOrder> findPage(Integer page, Integer size) {
        PageHelperUtils.Result<ReturnOrder> returnOrderResult = pageHelperUtils.pageHelperUtils(ReturnOrderMapper.class, null, page, size, "selectAll");
        return new PageResult<>(returnOrderResult.getTotal(), returnOrderResult.getList());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    @Override
    public List<ReturnOrder> findList(Map<String, Object> searchMap) {
        Example example = pageHelperUtils.createExample(searchMap, ReturnOrder.class);
        return returnOrderMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<ReturnOrder> findPage(Map<String, Object> searchMap, Integer page, Integer size) {
        Example example = pageHelperUtils.createExample(searchMap, ReturnOrder.class);
        PageHelperUtils.Result<ReturnOrder> returnOrderResult = pageHelperUtils.pageHelperUtils(ReturnOrderMapper.class, example, page, size, "selectByExample");
        return new PageResult<>(returnOrderResult.getTotal(), returnOrderResult.getList());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    @Override
    public ReturnOrder findById(Long id) {
        return returnOrderMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param returnOrder
     */
    public void add(ReturnOrder returnOrder) {
        returnOrderMapper.insert(returnOrder);
    }

    /**
     * 修改
     * @param returnOrder
     */
    @Override
    public void update(ReturnOrder returnOrder) {
        returnOrderMapper.updateByPrimaryKeySelective(returnOrder);
    }

    /**
     *  删除
     * @param id
     */
    @Override
    public void delete(Long id) {
        returnOrderMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    @Override
    public void agreeRefund(String id, Integer money, Integer adminId) {
        ReturnOrder returnOrder = returnOrderMapper.selectByPrimaryKey(id);
        if (returnOrder == null) throw new RuntimeException("退款订单不存在！");
        if (!returnOrder.getType().equals("2")) throw new RuntimeException("不是退款订单！");
        if (money > returnOrder.getReturnMoney() || money <= 0) throw new RuntimeException("退款金额不合法！");

        returnOrder.setReturnMoney(money);
        returnOrder.setStatus("1");                 // 同意
        returnOrder.setAdminId(adminId);            // 管理员
        returnOrder.setDisposeTime(new Date());     // 处理日期

        returnOrderMapper.updateByPrimaryKeySelective(returnOrder);

        // 调用支付平台的退款接口
    }

    @Override
    public void rejectRefund(String id, String remark, Integer adminId) {
        ReturnOrderItemMapper returnOrderItemMapper = SpringContextUtils.getBean(ReturnOrderItemMapper.class);
        OrderItemMapper orderItemMapper = SpringContextUtils.getBean(OrderItemMapper.class);

        ReturnOrder returnOrder = returnOrderMapper.selectByPrimaryKey(id);
        if (returnOrder == null) throw new RuntimeException("退款订单不存在！");
        if (!returnOrder.getType().equals("2")) throw new RuntimeException("不是退款订单！");
        if (remark.length() < 5) throw new RuntimeException("驳回理由至少 6 个字符");

        returnOrder.setRemark(remark);                                  // 设置驳回理由
        returnOrder.setStatus("2");                                     // 设置订单状态为驳回
        returnOrder.setAdminId(adminId);                                //管理员 id
        returnOrder.setDisposeTime(new Date());                         // 处理日期
        returnOrderMapper.updateByPrimaryKeySelective(returnOrder);

        // 修改对应订单明细的退款状态为未申请
        Example example = new Example(ReturnOrderItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("returnOrderId", id);               // 退款订单 id
        List<ReturnOrderItem> returnOrderItemList = returnOrderItemMapper.selectByExample(example);

        for (ReturnOrderItem returnOrderItem: returnOrderItemList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(String.valueOf(returnOrderItem.getOrderItemId())); // 提取订单明细 id
            orderItem.setIsReturn("0");

            orderItemMapper.updateByPrimaryKeySelective(orderItem);             // 更新状态，让其可以重新发送退款请求
        }
    }
}
