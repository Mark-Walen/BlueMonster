package cn.blue.phoenix.service.order;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.order.ReturnOrder;

import java.util.*;

/**
 * returnOrder业务逻辑层
 */
public interface ReturnOrderService {

    List<ReturnOrder> findAll();

    PageResult<ReturnOrder> findPage(Integer page, Integer size);

    List<ReturnOrder> findList(Map<String, Object> searchMap);

    PageResult<ReturnOrder> findPage(Map<String, Object> searchMap, Integer page, Integer size);

    ReturnOrder findById(Long id);

    void add(ReturnOrder returnOrder);

    void update(ReturnOrder returnOrder);

    void delete(Long id);

    void agreeRefund(String id, Integer money, Integer adminId);

    void rejectRefund(String id, String remark, Integer adminId);
}
