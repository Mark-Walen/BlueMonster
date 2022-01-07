package cn.blue.phoenix.controller.system;

import cn.blue.phoenix.pojo.system.Admin;
import cn.blue.phoenix.service.system.AdminService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname UserDetailServiceImpl
 * @Description TODO
 * @Date 2021/12/30 21:19
 * @Created by BlueVincent
 */
public class UserDetailServiceImpl implements UserDetailsService {

    @DubboReference
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Map<String, Object> map = new HashMap<>();
        map.put("loginName", s);
        map.put("status", "1");

        List<Admin> adminList = adminService.findList(map);

        if (adminList.size() == 0) return null;

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new User(s, adminList.get(0).getPassword(), grantedAuthorityList);
    }

    // 忘记密码暂时不做，因为需要进行信息验证
    public String updatePassword(String oldPassword, String newPassword) {
        // 获取当前登录用户的用户名
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        Map<String, Object> map = new HashMap<>();
        map.put("loginMame", username);
        List<Admin> adminList = adminService.findList(map);

        if (adminList.size() == 0) throw new UsernameNotFoundException("用户不存在或密码错误");

        Admin admin = adminList.get(0);
        String password = admin.getPassword();
        // 因为密码是加密的，所以不能直接比较
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        // 密码错误
        if (!bc.matches(oldPassword, password)) throw new UsernameNotFoundException("用户不存在或密码错误");
        // 新旧密码一致, 不是则返回
        if (password.equals(newPassword)) throw new UsernameNotFoundException("新、旧密码不能相同");
        else {
            admin.setPassword(newPassword);
            adminService.update(admin);
            return "1";
        }
    }
}
