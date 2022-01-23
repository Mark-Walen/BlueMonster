package cn.blue.phoenix.dao;

import cn.blue.phoenix.pojo.order.CategoryReport;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;
import tk.mybatis.mapper.common.Mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * <p>Date : 2022年01月23日 11:04</p>
 * <p>Project: BlueMonster</p>
 * <p>Package cn.blue.phoenix.dao</p>
 *
 * @author BlueVincent
 * @version V1.0
 */
public interface CategoryReportMapper extends Mapper<CategoryReport> {

    @Select("select category_id1 categoryId1, category_id2 categoryId2, category_id3 categoryId3, DATE_FORMAT(o.`pay_time`, '%Y-%m-%d') countDate, SUM(oi.num) num, SUM(oi.pay_money) money " +
    "from tb_order_item oi, tb_order o " +
    "where oi.`order_id`=o.`id` and o.`pay_status`='1' and DATE_FORMAT(o.`pay_time`, '%Y-%m-%d')=#{date} " +
    "group by `category_id1`,`category_id2`,`category_id3`,DATE_FORMAT(o.`pay_time`, '%Y-%m-%d')")
    List<CategoryReport> categoryReport(@Param("date")LocalDate date);

    @Select("select category_id1 categoryId1， c.name categoryName, SUM(num) num, SUM(money) money " +
    "from tb_category_report r, v_category1 c " +
    "where r.category_id1=c.id and count_date>=#{date1} and count_date<=#{date2} " +
    "group by category_id1, c.name")
    List<Map<Object, Object>> category1Count(@Param("date1") String date1, @Param("date2") String date2);
}
