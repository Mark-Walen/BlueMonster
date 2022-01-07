package cn.blue.phoenix.service.system;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.system.Menu;

import java.util.List;
import java.util.Map;

/**
 * @Classname MenuService
 * @Description TODO
 * @Date 2021/12/29 20:42
 * @Created by BlueVincent
 */
public interface MenuService {
    List<Menu> findAll();

    List<Map<String, Object>> findAllMenu();

    PageResult<Menu> findPage(Integer page, Integer size);

    List<Menu> findList(Map<String, Object> searchMap);

    PageResult<Menu> findPage(Map<String, Object> searchMap, Integer page, Integer size);

    Menu findById(String id);

    void add(Menu menu);

    void update(Menu menu);

    void delete(String id);
}
