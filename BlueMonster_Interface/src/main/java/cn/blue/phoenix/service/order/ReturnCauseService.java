package cn.blue.phoenix.service.order;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.order.ReturnCause;

import java.util.*;

/**
 * returnCause业务逻辑层
 */
public interface ReturnCauseService {

    List<ReturnCause> findAll();

    PageResult<ReturnCause> findPage(Integer page, Integer size);

    List<ReturnCause> findList(Map<String, Object> searchMap);

    PageResult<ReturnCause> findPage(Map<String, Object> searchMap, Integer page, Integer size);

    ReturnCause findById(Integer id);

    void add(ReturnCause returnCause);

    void update(ReturnCause returnCause);

    void delete(Integer id);
}
