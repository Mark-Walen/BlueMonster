package cn.blue.phoenix.service.system;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.system.Admin;

import java.util.List;
import java.util.Map;

/**
 * @Classname AdminService
 * @Description TODO
 * @Date 2021/12/27 16:58
 * @Created by BlueVincent
 */
public interface AdminService {
    List<Admin> findAll();


    PageResult<Admin> findPage(Integer page, Integer size);


    List<Admin> findList(Map<String, Object> searchMap);


    PageResult<Admin> findPage(Map<String, Object> searchMap, Integer page, Integer size);


    Admin findById(Integer id);

    void add(Admin admin);

    void update(Admin admin);

    void delete(Integer id);
}
