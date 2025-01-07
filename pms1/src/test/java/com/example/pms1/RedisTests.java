package com.example.pms1;

import com.example.pms1.util.RedisCache;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

/**
 * @author loself
 * @date 2024-07-01 19:14
 */

@SpringBootTest
public class RedisTests {

    @Autowired
    private RedisCache redisCache;

    @Resource
    private RedisCache redisCache1;

    @Test
    public  void getUsername(){
        String username="user1";
        username="login:"+username;
        String userdetails = redisCache.getCacheObject(username).toString();
        System.out.println(userdetails);
    }


    @Test
    public  void getUserByPhone(){
        String phoneNum="13514995984";
        String captcha="2345";
        System.out.println(redisCache);
        System.out.println(redisCache1);
        redisCache.setCacheObject("captcha of "+phoneNum,captcha,120, TimeUnit.SECONDS);
        String tcaptcha=redisCache.getCacheObject(phoneNum);
        System.out.println(tcaptcha);
        redisCache1.setCacheObject("captcha of "+phoneNum,captcha,120,TimeUnit.SECONDS);
        String mcaptcha=redisCache.getCacheObject(phoneNum);
        System.out.println(mcaptcha);


    }


}
