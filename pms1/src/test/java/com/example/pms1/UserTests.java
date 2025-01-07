package com.example.pms1;

import com.example.pms1.entity.User;
import com.example.pms1.service.UserInfoService;
import com.example.pms1.service.UserService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author loself
 * @date 2024-07-01 10:38
 */

@SpringBootTest
public class UserTests {

    @Autowired
    private UserService userService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserInfoService userInfoService;

    @Test
    void savenewuser(){
        User user = userService.selectUserByUsername("user3");
        if (user != null) {
            return;
        }

        user = new User();
        System.out.println(user);
        user.setUsername("user3");
        user.setPasswordHash(passwordEncoder.encode("123456"));
        user.setAvatarUrl("");
        user.setCreatedAt(new Timestamp(new Date().getTime()));
        user.setFullName("");
        user.setRole("user");
        user.setUpdatedAt(new Timestamp(new Date().getTime()));
        user.setPhoneNumber("1234561");
        System.out.println(user);
        userService.save(user);
    }

    @Test
    void testtime(){
        System.out.println(new Timestamp(new Date().getTime()));
    }

    @Test
    void getuser(){
        User user = userService.selectUserByUsername("user1");
        System.out.println(user);
    }

    @Test
    void edituser(){
        String token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIzYjZlYWQ3ODkyZTM0MTMxOTViNDAxMjg5YTYyN2U1YiIsInN1YiI6InVzZXIyIiwiaXNzIjoieGwiLCJpYXQiOjE3MTk5MDc4NTgsImV4cCI6MTcxOTkxMTQ1OH0.cgf-_SdfFygK0h7GyLcRXFECMdLxe5KqJ-itbEl8YLM";
        User user = new User();
        user.setFullName("");
        user.setRole("user");
        user.setUpdatedAt(new Timestamp(new Date().getTime()));
        user.setPhoneNumber("123456");
        System.out.println(user);
        userInfoService.editUserInfo(token,user);


    }
}
