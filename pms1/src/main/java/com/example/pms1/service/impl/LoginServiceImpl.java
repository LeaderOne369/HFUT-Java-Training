package com.example.pms1.service.impl;

import com.example.pms1.security.CaptchaAuthenticationToken;
import com.example.pms1.dto.HttpStatus;
import com.example.pms1.dto.LoginUser;
import com.example.pms1.dto.Resp;
import com.example.pms1.entity.User;
import com.example.pms1.service.LoginService;
import com.example.pms1.service.UserService;
import com.example.pms1.util.CaptchaUtil;
import com.example.pms1.util.JwtUtil;
import com.example.pms1.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author loself
 * @date 2024-07-01 10:18
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private CaptchaUtil captchaUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Resp login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = UsernamePasswordAuthenticationToken.unauthenticated(user.getUsername(), user.getPasswordHash());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authenticate)){
            return Resp.error(HttpStatus.BAD_REQUEST);
        }
        LoginUser loginUser=(LoginUser) authenticate.getPrincipal();
        String username = loginUser.getXxuser().getUsername().toString();
        String jwt = JwtUtil.createJWT(username);
        System.out.println(jwt);
        Map<String,String> map =new HashMap<>();
        Resp<Map<String, String>> resp = Resp.success(map);
        resp.setMsg("登录成功");
        map.put("token",jwt);
        redisCache.setCacheObject("login:"+username,loginUser);
        return resp ;
    }

    @Override
    public Resp loginByPhoneAndPassword(User user) {
        User xxuser=userService.selectUserByPhoneNumber(user.getPhoneNumber());
        xxuser.setPasswordHash(user.getPasswordHash());
        System.out.println(xxuser);
       return login(xxuser);
    }

    @Override
    public Resp loginBycaptcha(User user,String captcha) {
        try {
            boolean captchaflag=captchaUtil.checkCaptcha(user.getPhoneNumber(),captcha);
            if(captchaflag){
                User authenticatedUser = userService.selectUserByPhoneNumber(user.getPhoneNumber());
                if (authenticatedUser == null) {
                    return Resp.error(HttpStatus.LOGIN_ERROR_PHONE);
                }
                CaptchaAuthenticationToken authenticationToken = new CaptchaAuthenticationToken(authenticatedUser.getPhoneNumber(), captcha);
                Authentication authenticate = authenticationManager.authenticate(authenticationToken);
                if(Objects.isNull(authenticate)){
                    return Resp.error(HttpStatus.ACCOUNT_EXPIRED);
                }
                LoginUser loginUser=(LoginUser) authenticate.getPrincipal();
                String username = loginUser.getXxuser().getUsername().toString();
                String jwt = JwtUtil.createJWT(username);
                Map<String,String> map =new HashMap<>();
                Resp<Map<String, String>> resp = Resp.success(map);
                resp.setMsg("登录成功");
                map.put("token",jwt);
                redisCache.setCacheObject("login:"+username,loginUser);
                return resp ;
            }
            else {
                return Resp.error(HttpStatus.LOGIN_WRONG_CAPTCHA);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Resp forgetpassword(String phone, String captcha) {
        User user = userService.selectUserByPhoneNumber(phone);
        if(Objects.isNull(user)){
            return Resp.error(HttpStatus.LOGIN_ERROR_PHONE);
        }
        return Resp.success("允许密码重置");
    }



    @Override
    public Resp reset(String password, String phoneNumber) {
        User user = userService.selectUserByPhoneNumber(phoneNumber);
        user.setPasswordHash(passwordEncoder.encode(password));
        user.setUpdatedAt(new Timestamp(new Date().getTime()));
        boolean res = userService.updateById(user);
        if (res){
            return Resp.success("密码重置成功");
        }
        return Resp.error(HttpStatus.ERROR);

    }

    @Override
    public Resp logout(String token) {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String username = loginUser.getXxuser().getUsername().toString();
        try {
            String username1 = JwtUtil.parseJWT(token).getSubject();
            if(!username1.equals(username)){
                return Resp.error(HttpStatus.NO_PERMISSION);
            }
            redisCache.deleteObject("login:"+username);
            return Resp.success("注销成功");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resp checkAlive(String token) {
        try {
            Date expirationDate = JwtUtil.parseJWT(token).getExpiration();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String expirationTimeStr = sdf.format(expirationDate);
            return Resp.success("token有效期至："+expirationTimeStr);
        } catch (Exception e) {
            return Resp.error(HttpStatus.ACCOUNT_EXPIRED);
        }

    }
}
