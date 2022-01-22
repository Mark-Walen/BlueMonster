package cn.blue.phoenix.service.impl;

import cn.blue.phoenix.dao.ReturnCauseMapper;
import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.order.ReturnCause;
import cn.blue.phoenix.service.order.ReturnCauseService;
import cn.blue.phoenix.utils.PageHelperUtils;
import com.github.pagehelper.PageHelper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@DubboService(interfaceClass = ReturnCauseService.class)
public class ReturnCauseServiceImpl implements ReturnCauseService {

    @Autowired
    private ReturnCauseMapper returnCauseMapper;
    PageHelperUtils<ReturnCause> pageHelperUtils = new PageHelperUtils<>("cause", "status");

    /**
     * 返回全部记录
     * @return
     */
    @Override
    public List<ReturnCause> findAll() {
        return returnCauseMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    @Override
    public PageResult<ReturnCause> findPage(Integer page, Integer size) {
        PageHelperUtils.Result<ReturnCause> returnCauseResult = pageHelperUtils.pageHelperUtils(ReturnCauseMapper.class, null, page, size, "selectAll");
        return new PageResult<>(returnCauseResult.getTotal(), returnCauseResult.getList());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    @Override
    public List<ReturnCause> findList(Map<String, Object> searchMap) {
        Example example = pageHelperUtils.createExample(searchMap, ReturnCause.class);
        return returnCauseMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<ReturnCause> findPage(Map<String, Object> searchMap, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        Example example = pageHelperUtils.createExample(searchMap, ReturnCause.class);
        PageHelperUtils.Result<ReturnCause> returnCauseResult = pageHelperUtils.pageHelperUtils(ReturnCauseMapper.class, example, page, size, "selectAll");
        return new PageResult<>(returnCauseResult.getTotal(), returnCauseResult.getList());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public ReturnCause findById(Integer id) {
        return returnCauseMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param returnCause
     */
    public void add(ReturnCause returnCause) {
        returnCauseMapper.insert(returnCause);
    }

    /**
     * 修改
     * @param returnCause
     */
    public void update(ReturnCause returnCause) {
        returnCauseMapper.updateByPrimaryKeySelective(returnCause);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(Integer id) {
        returnCauseMapper.deleteByPrimaryKey(id);
    }
}
