package com.example.pms1.service.impl;

import com.example.pms1.dto.LoginUser;
import com.example.pms1.entity.User;
import com.example.pms1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author loself
 * @date 2024-07-02 10:53
 */

@Service
public class CaptchaUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;
    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user = userService.selectUserByPhoneNumber(phone);
        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        ArrayList<String> list = new ArrayList<>(Arrays.asList("test", "admin"));
        // 返回封装后的 UserDetails 对象
        return new LoginUser(user,list);
    }
}
