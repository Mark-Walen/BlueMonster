package cn.blue.phoenix.service.impl;

import cn.blue.phoenix.dao.LoginLogMapper;
import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.goods.Album;
import cn.blue.phoenix.pojo.system.LoginLog;
import cn.blue.phoenix.service.system.LoginLogService;
import cn.blue.phoenix.utils.PageHelperUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @Classname LoginLogServiceImpl
 * @Description TODO
 * @Date 2022/1/4 23:42
 * @Created by BlueVincent
 */
@DubboService(interfaceClass = LoginLogService.class)
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;

    private final PageHelperUtils<LoginLog> pageUtils = new PageHelperUtils<>("loginName");

    @Override
    public List<LoginLog> findAll() {
        return loginLogMapper.selectAll();
    }

    @Override
    public PageResult<LoginLog> findPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<LoginLog> list = loginLogMapper.selectAll();

        PageInfo<LoginLog> logPageInfo = new PageInfo<>(list);
        long total = logPageInfo.getTotal();
        List<LoginLog> loginLogList = logPageInfo.getList();
        return new PageResult<>(total, loginLogList);
    }

    @Override
    public List<LoginLog> findList(Map<String, Object> searchMap) {
        return null;
    }

    @Override
    public PageResult<LoginLog> findPage(Map<String, Object> searchMap, Integer page, Integer size) {
        Example example = pageUtils.createExample(searchMap, LoginLog.class);
        PageHelper.startPage(page, size);
        List<LoginLog> list = loginLogMapper.selectByExample(example);

        return new PageResult<>(pageUtils.getTotal(), pageUtils.pageHelperUtils(LoginLogMapper.class, page, size, "selectAll"));
    }

    @Override
    public LoginLog findById(Integer id) {
        return loginLogMapper.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public void add(LoginLog loginLog) {
        loginLogMapper.insert(loginLog);
    }

    @Transactional
    @Override
    public void update(LoginLog loginLog) {
        loginLogMapper.updateByPrimaryKeySelective(loginLog);
    }

    @Override
    public void delete(Integer id) {
        loginLogMapper.deleteByPrimaryKey(id);
    }
}
