package cn.blue.phoenix.service.impl;

import cn.blue.phoenix.dao.AdminMapper;
import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.system.Admin;
import cn.blue.phoenix.service.system.AdminService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @Classname AdminServiceImpl
 * @Description TODO
 * @Date 2021/12/27 17:35
 * @Created by BlueVincent
 */
@DubboService(interfaceClass = AdminService.class)
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Admin> findAll() {
        return adminMapper.selectAll();
    }

    @Override
    public PageResult<Admin> findPage(Integer page, Integer size) {
        return null;
    }

    @Override
    public List<Admin> findList(Map<String, Object> searchMap) {
        Example example = new Example(Admin.class);
        Example.Criteria criteria = example.createCriteria();

        for (Map.Entry<String, Object> entry: searchMap.entrySet()) {
            String key = entry.getKey();
            Object val = entry.getValue();

            if (val != null && !val.equals("")) {
                criteria.andEqualTo(key, val);
            }
        }
        return adminMapper.selectByExample(example);
    }

    @Override
    public PageResult<Admin> findPage(Map<String, Object> searchMap, Integer page, Integer size) {
        return null;
    }

    @Override
    public Admin findById(Integer id) {
        return null;
    }

    @Override
    public void add(Admin admin) {

    }

    @Override
    public void update(Admin admin) {
        adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public void delete(Integer id) {

    }
}
