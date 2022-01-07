package cn.blue.phoenix.service.impl;

import cn.blue.phoenix.dao.ParamMapper;
import cn.blue.phoenix.dao.TemplateMapper;
import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.goods.Param;
import cn.blue.phoenix.pojo.goods.Template;
import cn.blue.phoenix.service.goods.ParamService;
import cn.blue.phoenix.utils.PageHelperUtils;
import com.github.pagehelper.PageHelper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@DubboService(interfaceClass = ParamService.class)
public class ParamServiceImpl implements ParamService {

    @Autowired
    private ParamMapper paramMapper;

    @Autowired
    private TemplateMapper templateMapper;

    private final PageHelperUtils<Param> pageUtils = new PageHelperUtils<>();

    @Override
    public List<Param> findAll() {
        return paramMapper.selectAll();
    }

    @Override
    public PageResult<Param> findPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Param> list = paramMapper.selectAll();
        return new PageResult<>(pageUtils.getTotal(), pageUtils.pageHelperUtils(list, page, size));
    }

    @Override
    public List<Param> findList(Map<String, Object> searchMap) {
        Example example = pageUtils.createExample(searchMap, Param.class);
        return paramMapper.selectByExample(example);
    }

    @Override
    public PageResult<Param> findPage(Map<String, Object> searchMap, Integer page, Integer size) {
        Example example = pageUtils.createExample(searchMap, Param.class);
        PageHelper.startPage(page, size);
        List<Param> list = paramMapper.selectByExample(example);
        return new PageResult<>(pageUtils.getTotal(), pageUtils.pageHelperUtils(list, page, size));
    }

    @Override
    public Param findById(Integer id) {
        return paramMapper.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public void add(Param param) {
        paramMapper.insert(param);
        Template template = templateMapper.selectByPrimaryKey(param.getTemplateId());
        template.setSpecNum(template.getSpecNum() + 1);
        templateMapper.updateByPrimaryKeySelective(template);
    }

    @Override
    public void update(Param param) {
        paramMapper.updateByPrimaryKeySelective(param);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Param param = paramMapper.selectByPrimaryKey(id);
        Template template = templateMapper.selectByPrimaryKey(param.getTemplateId());
        template.setSpecNum(template.getSpecNum() - 1);
        templateMapper.updateByPrimaryKeySelective(template);

        paramMapper.deleteByPrimaryKey(id);
    }
}
