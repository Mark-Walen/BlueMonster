package cn.blue.phoenix.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Classname SystemPageController
 * @Description TODO
 * @Date 2021/12/27 16:30
 * @Created by BlueVincent
 */

@Controller
@RequestMapping("/system")
public class SystemPageController {

    @GetMapping("/login")
    public String getLogin() {
        return "system/login";
    }

    @GetMapping("/main")
    public String getMain() {
        return "system/main";
    }
}
