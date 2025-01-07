package com.example.pms1.service;

import com.example.pms1.dto.Resp;
import com.example.pms1.entity.User;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author loself
 * @date 2024-07-02 16:59
 */
public interface UserInfoService  {
    Resp getUserInfo(String token);

    Resp editUserInfo(String token, User user);

    Resp resetPassword(String token, String password, String newpassword);

    Resp uploadAvatar(String token, MultipartFile file);

}
