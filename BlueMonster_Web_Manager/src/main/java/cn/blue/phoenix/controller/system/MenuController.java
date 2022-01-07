package cn.blue.phoenix.controller.system;

import cn.blue.phoenix.service.system.MenuService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Classname MenuController
 * @Description TODO
 * @Date 2021/12/29 21:08
 * @Created by BlueVincent
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @DubboReference
    private MenuService menuService;

    @GetMapping("/findAllMenu")
    public List<Map<String, Object>> findMenu() {
        return menuService.findAllMenu();
    }
}
