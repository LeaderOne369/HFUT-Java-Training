package com.example.pms1.config;

/**
 * @author loself
 * @date 2024-07-08 10:43
 */
import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofMegabytes(100)); // 单个文件大小
        factory.setMaxRequestSize(DataSize.ofMegabytes(100)); // 整体请求大小
        return factory.createMultipartConfig();
    }
}
