package com.example.pms1.config;



/**
 * @author loself
 * @date 2024-07-08 17:02
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
@EnableWebMvc
public class StaticResourceConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String s = System.getProperty("user.dir") + File.separator + "avatars" + File.separator;
        registry.addResourceHandler("/avatars/**").addResourceLocations("file:"+s);
    }
}
