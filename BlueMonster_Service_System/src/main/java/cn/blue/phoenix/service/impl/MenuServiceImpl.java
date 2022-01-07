package cn.blue.phoenix.service.impl;

import cn.blue.phoenix.dao.MenuMapper;
import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.system.Menu;
import cn.blue.phoenix.service.system.MenuService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname MenuServiceImpl
 * @Description TODO
 * @Date 2021/12/29 20:54
 * @Created by BlueVincent
 */
@DubboService(interfaceClass = MenuService.class)
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findAll() {
        return menuMapper.selectAll();
    }

    @Override
    public List<Map<String, Object>> findAllMenu() {
        List<Menu> menuList = findAll();
        return findMenuListByParentId(menuList, "0");
    }

    @Override
    public PageResult<Menu> findPage(Integer page, Integer size) {
        return null;
    }

    @Override
    public List<Menu> findList(Map<String, Object> searchMap) {
        return null;
    }

    @Override
    public PageResult<Menu> findPage(Map<String, Object> searchMap, Integer page, Integer size) {
        return null;
    }

    @Override
    public Menu findById(String id) {
        return null;
    }

    @Override
    public void add(Menu menu) {

    }

    @Override
    public void update(Menu menu) {

    }

    @Override
    public void delete(String id) {

    }

    private List<Map<String, Object>> findMenuListByParentId(List<Menu> menuList, String parentId) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (Menu menu:menuList) {
            if (menu.getParentId().equals(parentId)) {
                Map<String, Object> map = new HashMap<>();
                map.put("path", menu.getId());
                map.put("title", menu.getName());
                map.put("icon", menu.getIcon());
                map.put("linkUrl", menu.getUrl());
                map.put("children", findMenuListByParentId(menuList, menu.getId()));
                mapList.add(map);
            }
        }
        return mapList;
    }
}
