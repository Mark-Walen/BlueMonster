package cn.blue.phoenix.service.impl;

import cn.blue.phoenix.dao.TemplateMapper;
import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.goods.Template;
import cn.blue.phoenix.service.goods.TemplateService;
import cn.blue.phoenix.utils.PageHelperUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@DubboService
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateMapper templateMapper;

    private final PageHelperUtils<Template> pageUtils = new PageHelperUtils<>();

    /**
     * @return 返回全部记录
     */
    @Override
    public List<Template> findAll() {
        return templateMapper.selectAll();
    }

    @Override
    public PageResult<Template> findPage(Integer page, Integer size) {
        List<Template> list = templateMapper.selectAll();
        return pageUtils.pageHelperUtils(list, page, size);
    }

    @Override
    public List<Template> findList(Map<String, Object> searchMap) {
        Example example = pageUtils.createExample(searchMap, Template.class);
        return templateMapper.selectByExample(example);
    }

    @Override
    public PageResult<Template> findPage(Map<String, Object> searchMap, Integer page, Integer size) {
        Example example = pageUtils.createExample(searchMap, Template.class);
        List<Template> list = templateMapper.selectByExample(example);
        return pageUtils.pageHelperUtils(list, page, size);
    }

    @Override
    public Template findById(Integer id) {
        return templateMapper.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public void add(Template template) {
        template.setSpecNum(0);
        template.setParaNum(0);
        templateMapper.insert(template);
    }

    @Override
    public void update(Template template) {
        templateMapper.updateByPrimaryKeySelective(template);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        templateMapper.deleteByPrimaryKey(id);
    }

}
