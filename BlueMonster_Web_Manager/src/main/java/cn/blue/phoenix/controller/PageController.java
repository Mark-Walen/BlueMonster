package cn.blue.phoenix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 静态页面跳转控制处理
 */
@Controller
@RequestMapping("/goods")
public class PageController {

    @GetMapping("/brand")
    public String getBrand() {
        return "goods/brand";
    }

    @GetMapping("/template")
    public String getTemplate() {
        return "goods/template";
    }
}
