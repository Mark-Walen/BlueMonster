package cn.blue.phoenix.service.order;

import cn.blue.phoenix.pojo.order.CategoryReport;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * <p>Date : 2022年01月23日 11:20</p>
 * <p>Project: BlueMonster</p>
 * <p>Package cn.blue.phoenix.service.order</p>
 *
 * @author BlueVincent
 * @version V1.0
 */
public interface CategoryReportService {
    /**
     *  商品类目按日期统计（订单表关联查询）
     */
    List<CategoryReport> categoryReport(LocalDate date);

    void createData();

    List<Map<Object, Object>> category1Count(String date1, String date2);
}
