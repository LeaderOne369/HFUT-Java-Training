package com.example.pms1.service.impl;

import com.example.pms1.dto.HttpStatus;
import com.example.pms1.dto.Resp;
import com.example.pms1.entity.User;
import com.example.pms1.service.RegisterService;
import com.example.pms1.service.UserService;
import com.example.pms1.util.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * @author loself
 * @date 2024-07-02 11:14
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private CaptchaUtil captchaUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Resp register(User user, String captcha) {
        boolean validate = captchaUtil.checkCaptcha(user.getPhoneNumber(), captcha);
        if(validate){
            User xxuser=userService.selectUserByUsername(user.getUsername());
            if(!Objects.isNull(xxuser)){
                return Resp.error(HttpStatus.REGISTER_USEREXISTS);
            }
            User xyuser = userService.selectUserByPhoneNumber(user.getPhoneNumber());
            if(!Objects.isNull(xyuser)){
                return Resp.error(HttpStatus.REGISTER_PHONEXISTS);
            }
            return registerUser(user);

        }
        else {
            return Resp.error(HttpStatus.LOGIN_WRONG_CAPTCHA);
        }

    }

    private Resp registerUser(User user) {
        user.setRole("user");
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        user.setCreatedAt(new Timestamp(new Date().getTime()));
        user.setAvatarUrl("");
        user.setUpdatedAt(new Timestamp(new Date().getTime()));
        userService.save(user);
        return Resp.success("注册成功");
    }
}
