package cn.blue.phoenix.controller.order;

import cn.blue.phoenix.pojo.order.CategoryReport;
import cn.blue.phoenix.service.order.CategoryReportService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * <p>Date: 2022年01月23日 11:25</p>
 * <p>Project: BlueMonster</p>
 * <p>Package: cn.blue.phoenix.controller.order</p>
 *
 * @author BlueVincent
 * @version V1.0
 */
@RestController
@RequestMapping("/categoryReport")
public class CategoryReportController {

    @DubboReference
    private CategoryReportService categoryReportService;

    @GetMapping("/yesterday")
    public List<CategoryReport> yesterday() {
        LocalDate localDate = LocalDate.now().minusDays(1);
        return categoryReportService.categoryReport(localDate);
    }

    @GetMapping("/category1Count")
    List<Map<Object, Object>> category1Count(String date1, String date2) {
        return categoryReportService.category1Count(date1, date2);
    }
}
