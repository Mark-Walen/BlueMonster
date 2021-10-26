package cn.blue.phoenix.utils;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.goods.Brand;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PageHelperUtils<T> {

    public PageResult<T> pageHelperUtils(List<T> list, Integer page, Integer size) {

        PageHelper.startPage(page, size);

        // 封装查询结果
        PageInfo<T> pageInfo = new PageInfo<>(list);

        // 获取记录总数
        long total = pageInfo.getTotal();
        // 获取当前页数列表
        List<T> tList = pageInfo.getList();
        return new PageResult<>(total, tList);
    }

    public Example createExample(Map<String, Object> searchMap, java.lang.Class<?> entityClass) {
        Example example = new Example(entityClass);
        Example.Criteria criteria = example.createCriteria();

        // 遍历搜索列表
        for (Map.Entry<String, Object> entry: searchMap.entrySet()) {
            String key = entry.getKey();
            Object val = entry.getValue();
            if (key.equals("name")) {
                if (val != null && !"".equals(val)) {
                    criteria.andLike("name","%"+searchMap.get("name")+"%");
                }
            } else {
                if (val != null) {
                    criteria.andEqualTo(key, val);
                }
            }
        }
        return example;
    }
}
