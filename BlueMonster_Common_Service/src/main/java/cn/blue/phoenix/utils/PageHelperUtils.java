package cn.blue.phoenix.utils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PageHelperUtils<T> {

    private final HashSet<String> likeSet;
    private long total;

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
     * <p>如果实体类不包含 name 这个属性，可以传入空字符串，打开 {@code this.likeSet.remove("");} 注释。</p>
     * <p>当然也可以直接调用无参构造，因为在进行查询会判断 searchMap 是否有 name 这个属性</p>
     *
     * @param values 模糊查询数据列表
     */
    public PageHelperUtils(String... values) {
        this.likeSet = Stream.of(values).collect(Collectors.toCollection(HashSet::new));
        // this.likeSet.remove("");
    }

    /**
     * @param list 从数据库中查询到的数据列表
     * @param page 请求第 page 页数据
     * @param size 当前 page 页中数据的数目
     * @return 查询结果 PageResult<T>
     */
    public List<T> pageHelperUtils(List<T> list, Integer page, Integer size) {
        // 封装查询结果
        PageInfo<T> pageInfo = new PageInfo<>(list);

        // 获取记录总数
        this.setTotal(pageInfo.getTotal());
        // 获取当前页数列表
        List<T> tList = pageInfo.getList();
        System.out.println(tList.size());
        return tList;
    }

    /**
     * 通过传递 mapperName 获取对应已实例化的 xxxMapper 对象，然后获取数据
     *
     * @param page 请求第 page 页数据
     * @param size 当前 page 页中数据的数目
     * @param type 继承了 import tk.mybatis.mapper.common.Mapper 的类
     * @param operationType 操作类型，即 tk.mybatis.mapper.common.Mapper <br>提供的增删改查方法名，目前不支持需要传参的方法
     * @return 查询结果 PageResult<T>
     */
    @SuppressWarnings("unchecked")
    public List<T> pageHelperUtils(Integer page, Integer size, Class<? extends Mapper<T>> type, String operationType) {
        Mapper<T> mapper = SpringContextUtils.getBean(type);
        try {
            PageHelper.startPage(page, size);
            Method method = type.getMethod(operationType);
            List<T> tlist= (List<T>) method.invoke(mapper);
            return this.pageHelperUtils(tlist, page, size);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回一个 Example 对象
     *
     * @param searchMap   查询列表
     * @param entityClass 实体类 class
     * @return 根据 searchMap 中的字段，返回带有每个字段需要进行什么样的查询操作的 Example 对象，如等于，模糊查询等。
     */
    public Example createExample(Map<String, Object> searchMap, Class<?> entityClass) {
        Example example = new Example(entityClass);
        Example.Criteria criteria = example.createCriteria();

        // 遍历搜索列表
        for (Map.Entry<String, Object> entry : searchMap.entrySet()) {
            String key = entry.getKey();
            Object val = entry.getValue();
            if (this.likeSet.contains(key)) {
                if (val != null && !"".equals(val)) {
                    criteria.andLike(key, "%" + val + "%");
                }
            } else {
                if (val != null) {
                    criteria.andEqualTo(key, val);
                }
            }
        }
        return example;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getTotal() {
        return total;
    }
}
