package com.example.pms1.config;

import com.example.pms1.security.CaptchaAuthenticationProvider;
import com.example.pms1.dto.HttpStatus;
import com.example.pms1.dto.Resp;
import com.example.pms1.filter.JwtAuthenticationTokenFilter;
import com.example.pms1.util.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

/**
 * @author loself
 * @date 2024-07-01 9:54
 */

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private CaptchaAuthenticationProvider captchaAuthenticationProvider;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CsrfFilter 禁用CSRF
        http.csrf(AbstractHttpConfigurer::disable);

        http.exceptionHandling(config -> config
                .authenticationEntryPoint((request, response, exception) -> ServletUtil.print(response, Resp.error(HttpStatus.FORBIDDEN)))
                .accessDeniedHandler((request, response, exception) -> ServletUtil.print(response, Resp.error(HttpStatus.FORBIDDEN)))
        );

        // AuthorizationFilter 授权，除匿名接口外都需要认证
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/user/**").permitAll()
                .requestMatchers("/sendcaptcha").permitAll()
                .requestMatchers("/avatars/**").permitAll()
                .anyRequest().authenticated()
        );

        // headers 处理
        http.headers(header -> {
            header.cacheControl(HeadersConfigurer.CacheControlConfig::disable);
            header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable);
        });

//        // TokenFilter 通过 Token 实现认证
//        http.addFilterBefore(new TokenFilter(), AuthorizationFilter.class);
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }



    /**
     * 配置认证管理器
     *
     * @return 认证管理器
     */
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(Arrays.asList(authenticationProvider,captchaAuthenticationProvider));
    }



}