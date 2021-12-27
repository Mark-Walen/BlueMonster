package cn.blue.phoenix.controller.user;

import cn.blue.phoenix.pojo.system.Admin;
import cn.blue.phoenix.service.system.AdminService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname UserServiceImpl
 * @Description TODO
 * @Date 2021/12/27 19:11
 * @Created by BlueVincent
 */
public class UserServiceImpl implements UserDetailsService {

    @DubboReference
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Map<String, Object> map = new HashMap<>();
        map.put("loginName", s);
        map.put("status", 1);

        List<Admin> adminList = adminService.findList(map);
        System.out.println(adminList.size());
        if (adminList.size() == 0) return null;
        for (Admin admin:adminList) {
            System.out.println(admin.getPassword());
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return new User(s, adminList.get(0).getPassword(), grantedAuthorities);
    }
}
