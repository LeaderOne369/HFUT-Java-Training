package com.example.pms1.controller;

import com.example.pms1.dto.HttpStatus;
import com.example.pms1.dto.Resp;
import com.example.pms1.entity.User;
import com.example.pms1.service.LoginService;
import com.example.pms1.util.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author loself
 * @date 2024-07-01 10:17
 */

/**
 * 用于登录的相关接口
 */
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private CaptchaUtil captchaUtil;


    /**
     * 用户登录的主要接口
     * @param user  登录提交的用户信息，会根据登录类型的不同读取不同的用户信息
     * @param type  登录的方式，包括 "phone/password","username/password","phone/captcha"
     * @param captcha   验证码，若所选登录方式不含验证码，则默认为空
     * @return
     */
    @PostMapping("user/login")
    public Resp login(@RequestBody User user,@RequestParam String type,@RequestParam(defaultValue = "") String captcha){
        if("phone/password".equals(type)){
            return loginService.loginByPhoneAndPassword(user);
        } else if ("username/password".equals(type)) {
            return loginService.login(user);
        } else if ("phone/captcha".equals(type)) {
            return loginService.loginBycaptcha(user,captcha);
        } else {
            return Resp.error(HttpStatus.LOGIN_ERROR_TYPE);
        }

    }

    /**
     * 简单的检测接口
     * @return
     */
    @GetMapping("/hello")
    public String hello(){

        return "hello";
    }

    /**
     * 发送验证码的接口
     * @param phone  要发送的手机号
     * @return
     */

    @RequestMapping("/sendcaptcha")
    public Resp sendcaptcha(@RequestParam String phone){
        try {
            captchaUtil.sendsms(phone);
            Resp<String> success = Resp.success("");
            success.setMsg("验证码发送成功,有效期两分钟");
            return success;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 用户用于忘记密码的身份验证，通过后才可以使用密码重置接口
     * @param phone     用户填写的手机号
     * @param captcha   用户填写的验证码
     * @return
     */

    @PostMapping("/user/forgetvalidate")
    public Resp forgetPassword(@RequestParam String phone,@RequestParam String captcha){
        return loginService.forgetpassword(phone,captcha);

    }

    /**
     * 用户身份验证通过后，用于重置密码的接口
     * @param password  重置后的密码
     * @param phoneNumber   用户身份检测使用的手机号
     * @return
     */
    @PostMapping("/user/reset")
    public Resp reset(@RequestBody String password,@RequestParam String phoneNumber){
        return loginService.reset(password,phoneNumber);
    }

    /**
     * 用户退出登录
     * @param token 用户携带信息
     * @return
     */
    @RequestMapping("/logout")
    public Resp logout(@RequestHeader String token){
        return loginService.logout(token);
    }

    /**
     * 检测token状态
     * @param token 用户携带信息
     * @return
     */
    @GetMapping("/checkalive")
    public Resp checkAlive(@RequestHeader String token){
        return loginService.checkAlive(token);
    }


}
