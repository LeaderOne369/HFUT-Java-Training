package com.example.pms1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author loself
 * @date 2024-07-01 23:59
 */

@SpringBootTest
public class PasswordEncoderTests {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void getoldpassword(){
        boolean ret= passwordEncoder.upgradeEncoding("$10$kzeFS4VvA47PM566ehY3HucmOqIzlW8qL2IhteyI4LPbUrY3qiNWG");
        System.out.println(ret);
    }
}
