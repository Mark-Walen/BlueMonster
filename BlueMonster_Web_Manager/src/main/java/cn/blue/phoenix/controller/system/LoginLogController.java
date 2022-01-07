package cn.blue.phoenix.controller.system;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.system.LoginLog;
import cn.blue.phoenix.service.system.LoginLogService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname LoginLogController
 * @Description TODO
 * @Date 2022/1/7 0:30
 * @Created by BlueVincent
 */

@RestController
@RequestMapping("/Log")
public class LoginLogController {

    @DubboReference
    private LoginLogService loginLogService;

    @GetMapping("/login/findPageByLogin")
    public PageResult<LoginLog> findPageByLogin(Integer page, Integer size) {
        // 添加条件
        String loginName = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> map = new HashMap<>();
        map.put("loginName", loginName);
        return loginLogService.findPage(map, page, size);
    }
}
