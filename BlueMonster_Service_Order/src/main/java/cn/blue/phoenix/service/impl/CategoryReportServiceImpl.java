package cn.blue.phoenix.service.impl;

import cn.blue.phoenix.dao.CategoryReportMapper;
import cn.blue.phoenix.pojo.order.CategoryReport;
import cn.blue.phoenix.service.order.CategoryReportService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * <p>Date: 2022年01月23日 11:23</p>
 * <p>Project: BlueMonster</p>
 * <p>Package: cn.blue.phoenix.service.impl</p>
 *
 * @author BlueVincent
 * @version V1.0
 */
@DubboService(interfaceClass = CategoryReportService.class)
public class CategoryReportServiceImpl implements CategoryReportService {
    
    @Autowired
    private CategoryReportMapper categoryReportMapper;
    
    @Override
    public List<CategoryReport> categoryReport(LocalDate date) {
        return categoryReportMapper.categoryReport(date);
    }

    @Transactional
    @Override
    public void createData() {
        LocalDate localDate = LocalDate.now().minusDays(1);     // 得到昨天的日期

        List<CategoryReport> categoryReports = categoryReportMapper.categoryReport(localDate);

        for (CategoryReport categoryReport : categoryReports) {
            categoryReportMapper.insert(categoryReport);
        }
    }

    @Override
    public List<Map<Object, Object>> category1Count(String date1, String date2) {
        return categoryReportMapper.category1Count(date1, date2);
    }
}
