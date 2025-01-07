package com.example.pms1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.pms1.entity.User;
import com.example.pms1.mapper.UserMapper;
import com.example.pms1.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author loself
 * @date 2024-07-01 10:14
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User selectUserByUsername(String username) {
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getUsername, username); // 账号，在新增时需控制
        qw.last("limit 1");
        return getOne(qw);
    }

    @Override
    public User selectUserByPhoneNumber(String phoneNumber) {
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getPhoneNumber, phoneNumber).last("limit 1");
        return getOne(qw);
    }


}
