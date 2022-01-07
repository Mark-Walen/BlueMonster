package cn.blue.phoenix.controller.system;

import cn.blue.phoenix.pojo.system.LoginLog;
import cn.blue.phoenix.service.system.LoginLogService;
import cn.blue.phoenix.utils.WebUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Spring Security 登录处理器
 * @Classname AuthenticationSuccessHandlerImpl
 * @Description TODO
 * @Date 2022/1/4 23:07
 * @author by BlueVincent
 */
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

    @DubboReference
    private LoginLogService loginLogService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        System.out.println("登录成功处理器到此一游");
        LoginLog loginLog = new LoginLog();

        String loginName = authentication.getName();
        String ip = httpServletRequest.getRemoteAddr();
        loginLog.setLoginName(loginName);
        loginLog.setLoginTime(new Date());
        loginLog.setIp(ip);
        loginLog.setLocation(WebUtil.getCityByIP(ip));
        loginLog.setBrowserName(WebUtil.getBrowserName(httpServletRequest.getHeader("user-agent")));

        loginLogService.add(loginLog);
        httpServletRequest.getRequestDispatcher("/system/main").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletRequest.getRequestDispatcher("/system/login").forward(httpServletRequest, httpServletResponse);
    }
}
