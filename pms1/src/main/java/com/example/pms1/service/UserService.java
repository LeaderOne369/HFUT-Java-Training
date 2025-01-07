package com.example.pms1.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pms1.entity.User;

/**
 * @author loself
 * @date 2024-07-01 10:13
 */


public interface UserService extends IService<User> {

    User selectUserByUsername(String username);

    User selectUserByPhoneNumber(String phoneNumber);


}
