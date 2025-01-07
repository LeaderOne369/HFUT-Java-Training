package com.example.pms1.filter;

import com.example.pms1.dto.HttpStatus;
import com.example.pms1.dto.LoginUser;
import com.example.pms1.dto.Resp;
import com.example.pms1.util.JwtUtil;
import com.example.pms1.util.RedisCache;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

/**
 * @author loself
 * @date 2024-07-01 9:55
 */


@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private RedisCache redisCache;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String xxtoken = request.getHeader("token");
        System.out.println("xxtoken:"+xxtoken);
        if (!StringUtils.hasText(xxtoken)) {
            //如果请求没有携带token直接放行
            filterChain.doFilter(request, response);
            return ;
        }
        //解析token
        String username;
        try {
            Claims claims = JwtUtil.parseJWT(xxtoken);
            System.out.println(claims);
            username = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        String redisKey = "login:" + username;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        if(Objects.isNull(loginUser)){
            throw new RuntimeException("用户未登录");
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //全部做完之后，就放行
        filterChain.doFilter(request, response);
    }


}

