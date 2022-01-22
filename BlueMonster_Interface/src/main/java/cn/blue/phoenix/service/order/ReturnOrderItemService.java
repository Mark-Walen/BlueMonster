package cn.blue.phoenix.service.order;
import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.order.ReturnOrderItem;

import java.util.*;

/**
 * returnOrderItem业务逻辑层
 */
public interface ReturnOrderItemService {


    List<ReturnOrderItem> findAll();


    PageResult<ReturnOrderItem> findPage(Integer page, Integer size);


    List<ReturnOrderItem> findList(Map<String,Object> searchMap);


    PageResult<ReturnOrderItem> findPage(Map<String, Object> searchMap, Integer page, Integer size);


    ReturnOrderItem findById(Long id);

    void add(ReturnOrderItem returnOrderItem);


    void update(ReturnOrderItem returnOrderItem);


    void delete(Long id);

}
