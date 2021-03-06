package cn.blue.phoenix.service.impl;

import cn.blue.phoenix.dao.SkuMapper;
import cn.blue.phoenix.dao.SpecMapper;
import cn.blue.phoenix.dao.TemplateMapper;
import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.goods.Spec;
import cn.blue.phoenix.pojo.goods.Template;
import cn.blue.phoenix.service.goods.SpecService;
import cn.blue.phoenix.utils.PageHelperUtils;
import com.github.pagehelper.PageHelper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@DubboService(interfaceClass = SpecService.class)
public class SpecServiceImpl implements SpecService {

    @Autowired
    private SpecMapper specMapper;

    @Autowired
    private TemplateMapper templateMapper;

    private final PageHelperUtils<Spec> pageUtils = new PageHelperUtils<>();

    @Override
    public List<Spec> findAll() {
        return specMapper.selectAll();
    }

    @Override
    public PageResult<Spec> findPage(Integer page, Integer size) {
        PageHelperUtils.Result<Spec> specResult = pageUtils.pageHelperUtils(SpecMapper.class, null, page, size, "selectAll");
        return new PageResult<>(specResult.getTotal(), specResult.getList());
    }

    @Override
    public List<Spec> findList(Map<String, Object> searchMap) {
        Example example = pageUtils.createExample(searchMap, Spec.class);
        return specMapper.selectByExample(example);
    }

    @Override
    public PageResult<Spec> findPage(Map<String, Object> searchMap, Integer page, Integer size) {
        Example example = pageUtils.createExample(searchMap, Spec.class);
        PageHelperUtils.Result<Spec> specResult = pageUtils.pageHelperUtils(SpecMapper.class, example, page, size, "selectByExample");
        return new PageResult<>(specResult.getTotal(), specResult.getList());
    }

    @Override
    public Spec findById(Integer id) {
        return specMapper.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public void add(Spec spec) {
        specMapper.insert(spec);
        Template template = templateMapper.selectByPrimaryKey(spec.getTemplateId());
        template.setSpecNum(template.getSpecNum() + 1);
        templateMapper.updateByPrimaryKeySelective(template);
    }

    @Override
    public void update(Spec spec) {
        specMapper.updateByPrimaryKeySelective(spec);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Spec spec = specMapper.selectByPrimaryKey(id);
        Template template = templateMapper.selectByPrimaryKey(spec.getTemplateId());
        template.setSpecNum(template.getSpecNum() - 1);
        templateMapper.updateByPrimaryKeySelective(template);

        specMapper.deleteByPrimaryKey(id);
    }
}
