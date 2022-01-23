package cn.blue.phoenix.controller.report;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Date: 2022年01月23日 12:33</p>
 * <p>Project: BlueMonster</p>
 * <p>Package: cn.blue.phoenix.controller.report</p>
 * description report 页面视图控制
 *
 * @author BlueVincent
 * @version V1.0
 */
@Controller
@RequestMapping("/report")
public class ReportPageController {

    @GetMapping("/category_report")
    public String categoryReport() {
        return "report/category_report";
    }
}
