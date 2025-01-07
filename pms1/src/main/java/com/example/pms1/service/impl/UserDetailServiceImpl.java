package com.example.pms1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.pms1.dto.LoginUser;
import com.example.pms1.entity.User;
import com.example.pms1.mapper.UserMapper;
import com.example.pms1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author loself
 * @date 2024-07-01 9:43
 */

@Service
@Primary
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        LambdaQueryWrapper<Object> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(User::getUsername,username);
//        User user = userMapper.selectOne(queryWrapper);
        User user = userService.selectUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        ArrayList<String> list = new ArrayList<>(Arrays.asList("test", "admin"));
        // 返回封装后的 UserDetails 对象
        return new LoginUser(user,list);

    }
}
