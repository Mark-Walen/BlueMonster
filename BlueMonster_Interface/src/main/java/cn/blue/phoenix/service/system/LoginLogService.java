package cn.blue.phoenix.service.system;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.system.LoginLog;

import java.util.List;
import java.util.Map;

/**
 * @Classname LoginLogService
 * @Description TODO
 * @Date 2022/1/4 23:36
 * @Created by BlueVincent
 */
public interface LoginLogService {
    List<LoginLog> findAll();

    PageResult<LoginLog> findPage(Integer page, Integer size);

    List<LoginLog> findList(Map<String, Object> searchMap);

    PageResult<LoginLog> findPage(Map<String, Object> searchMap, Integer page, Integer size);

    LoginLog findById(Integer id);

    void add(LoginLog loginLog);

    void update(LoginLog loginLog);

    void delete(Integer id);
}
