package cn.blue.phoenix.service.impl;

import cn.blue.phoenix.dao.PreferentialMapper;
import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.order.Preferential;
import cn.blue.phoenix.service.order.PreferentialService;
import cn.blue.phoenix.utils.PageHelperUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@DubboService
public class PreferentialServiceImpl implements PreferentialService {

    @Autowired
    private PreferentialMapper preferentialMapper;

    private final PageHelperUtils<Preferential> pageHelperUtils = new PageHelperUtils<>("state", "type");

    /**
     * 返回全部记录
     * @return
     */
    @Override
    public List<Preferential> findAll() {
        return preferentialMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    @Override
    public PageResult<Preferential> findPage(Integer page, Integer size) {
        PageHelperUtils.Result<Preferential> preferentialResult = pageHelperUtils.pageHelperUtils(PreferentialMapper.class, null, page, size, "selectAll");
        return new PageResult<Preferential>(preferentialResult.getTotal(), preferentialResult.getList());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    @Override
    public List<Preferential> findList(Map<String, Object> searchMap) {
        Example example = pageHelperUtils.createExample(searchMap, Preferential.class);
        return preferentialMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<Preferential> findPage(Map<String, Object> searchMap, Integer page, Integer size) {
        Example example = pageHelperUtils.createExample(searchMap, Preferential.class);
        PageHelperUtils.Result<Preferential> preferentialResult = pageHelperUtils.pageHelperUtils(PreferentialMapper.class, example, page, size, "selectByExample");
        return new PageResult<>(preferentialResult.getTotal(), preferentialResult.getList());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    @Override
    public Preferential findById(Integer id) {
        return preferentialMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param preferential
     */
    @Override
    public void add(Preferential preferential) {
        preferentialMapper.insert(preferential);
    }

    /**
     * 修改
     * @param preferential
     */
    @Override
    public void update(Preferential preferential) {
        preferentialMapper.updateByPrimaryKeySelective(preferential);
    }

    /**
     *  删除
     * @param id
     */
    @Override
    public void delete(Integer id) {
        preferentialMapper.deleteByPrimaryKey(id);
    }
}
