package cn.blue.phoenix.service.impl;

import cn.blue.phoenix.dao.ReturnOrderItemMapper;
import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.order.ReturnOrderItem;
import cn.blue.phoenix.service.order.ReturnOrderItemService;
import cn.blue.phoenix.utils.PageHelperUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@DubboService(interfaceClass = ReturnOrderItemService.class)
public class ReturnOrderItemServiceImpl implements ReturnOrderItemService {

    @Autowired
    private ReturnOrderItemMapper returnOrderItemMapper;
    private final PageHelperUtils<ReturnOrderItem> pageHelperUtils = new PageHelperUtils<>("title", "image");

    /**
     * 返回全部记录
     * @return
     */
    @Override
    public List<ReturnOrderItem> findAll() {
        return returnOrderItemMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    @Override
    public PageResult<ReturnOrderItem> findPage(Integer page, Integer size) {
        PageHelperUtils.Result<ReturnOrderItem> returnOrderItemResult = pageHelperUtils.pageHelperUtils(ReturnOrderItemMapper.class, null, page, size, "selectAll");
        return new PageResult<>(returnOrderItemResult.getTotal(), returnOrderItemResult.getList());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    @Override
    public List<ReturnOrderItem> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return returnOrderItemMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<ReturnOrderItem> findPage(Map<String, Object> searchMap, Integer page, Integer size) {
        Example example = pageHelperUtils.createExample(searchMap, ReturnOrderItem.class);
        PageHelperUtils.Result<ReturnOrderItem> returnOrderItemResult = pageHelperUtils.pageHelperUtils(ReturnOrderItemMapper.class, example, page, size, "selectAll");
        return new PageResult<>(returnOrderItemResult.getTotal(), returnOrderItemResult.getList());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    @Override
    public ReturnOrderItem findById(Long id) {
        return returnOrderItemMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param returnOrderItem
     */
    public void add(ReturnOrderItem returnOrderItem) {
        returnOrderItemMapper.insert(returnOrderItem);
    }

    /**
     * 修改
     * @param returnOrderItem
     */
    public void update(ReturnOrderItem returnOrderItem) {
        returnOrderItemMapper.updateByPrimaryKeySelective(returnOrderItem);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(Long id) {
        returnOrderItemMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(ReturnOrderItem.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 标题
            if(searchMap.get("title")!=null && !"".equals(searchMap.get("title"))){
                criteria.andLike("title","%"+searchMap.get("title")+"%");
            }
            // 图片地址
            if(searchMap.get("image")!=null && !"".equals(searchMap.get("image"))){
                criteria.andLike("image","%"+searchMap.get("image")+"%");
            }

            // 单价
            if(searchMap.get("price")!=null ){
                criteria.andEqualTo("price",searchMap.get("price"));
            }
            // 数量
            if(searchMap.get("num")!=null ){
                criteria.andEqualTo("num",searchMap.get("num"));
            }
            // 总金额
            if(searchMap.get("money")!=null ){
                criteria.andEqualTo("money",searchMap.get("money"));
            }
            // 支付金额
            if(searchMap.get("payMoney")!=null ){
                criteria.andEqualTo("payMoney",searchMap.get("payMoney"));
            }
            // 重量
            if(searchMap.get("weight")!=null ){
                criteria.andEqualTo("weight",searchMap.get("weight"));
            }

        }
        return example;
    }

}
