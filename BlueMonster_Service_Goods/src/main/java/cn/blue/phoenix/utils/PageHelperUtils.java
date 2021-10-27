package cn.blue.phoenix.utils;

import cn.blue.phoenix.entity.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PageHelperUtils<T> {

    private final HashSet<String> likeSet;

    /**
     * 默认初始化 likeSet 包含一个 "name", 进行模糊查询
     */
    public PageHelperUtils() {
        this.likeSet = new HashSet<>();
        this.likeSet.add("name");
    }

    /**
     * 将 <b>构造函数参数列表</b> 转化为 {@code Stream} 对象，进一步将 stream 转换为 HashSet 初始化 likeSet
     *
     * <p><b>可以传入 String 类型数组</b>，{@code Stream.of(T... values)}
     * 底层会使用 {@code Arrays.stream(values)} 将 数组转化为 {@code Stream} 对象</p>
     * @param values 模糊查询数据列表
     */
    public PageHelperUtils(String... values) {
        this.likeSet = Stream.of(values).collect(Collectors.toCollection(HashSet::new));
    }

    /**
     *
     * @param list 从数据库中查询到的数据列表
     * @param page 请求第 page 页数据
     * @param size 当前 page 页中数据的数目
     * @return 查询结果 PageResult<T>
     */
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

    /**
     * 返回一个 Example 对象
     * @param searchMap 查询列表
     * @param entityClass 实体类 class
     * @return 根据 searchMap 中的字段，返回带有每个字段需要进行什么样的查询操作的 Example 对象，如等于，模糊查询等。
     */
    public Example createExample(Map<String, Object> searchMap, java.lang.Class<?> entityClass) {
        Example example = new Example(entityClass);
        Example.Criteria criteria = example.createCriteria();

        // 遍历搜索列表
        for (Map.Entry<String, Object> entry: searchMap.entrySet()) {
            String key = entry.getKey();
            Object val = entry.getValue();
            if (this.likeSet.contains(key)) {
                if (val != null && !"".equals(val)) {
                    criteria.andLike(key,"%"+ val +"%");
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
