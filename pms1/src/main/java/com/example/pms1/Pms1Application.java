package com.example.pms1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.pms1.mapper")
public class Pms1Application {

    public static void main(String[] args) {
        SpringApplication.run(Pms1Application.class, args);
    }

}
