package com.example.pms1.controller;

import com.example.pms1.dto.Resp;
import com.example.pms1.entity.User;
import com.example.pms1.service.RegisterService;
import com.example.pms1.util.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author loself
 * @date 2024-07-02 11:13
 */


/**
 * 用户用于注册的相关接口
 */
@RestController
public class RegisterController {
    @Autowired
    private  RegisterService registerService;

    @Autowired
    private CaptchaUtil captchaUtil;

    /**
     * 用户用于注册的接口
     * @param user  注册的用户信息
     * @param captcha   发送的验证码
     * @return
     */
    @PostMapping("user/register")
    public Resp register(@RequestBody User user, @RequestParam String captcha){

        return registerService.register(user,captcha);
    }


}
