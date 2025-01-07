package com.example.pms1.service;

import com.example.pms1.dto.Resp;
import com.example.pms1.entity.User;

/**
 * @author loself
 * @date 2024-07-01 10:17
 */
public interface LoginService {
    Resp login(User user);

    Resp loginByPhoneAndPassword(User user);

    Resp loginBycaptcha(User user,String captcha);

    Resp forgetpassword(String phone, String captcha);

    Resp reset(String password,String phone);

    Resp logout(String token);

    Resp checkAlive(String token);
}
