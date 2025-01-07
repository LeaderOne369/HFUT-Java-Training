package com.example.pms1.controller;

import com.example.pms1.dto.ChangePasswordDTO;
import com.example.pms1.dto.Resp;
import com.example.pms1.entity.User;
import com.example.pms1.service.UserInfoService;
import com.example.pms1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author loself
 * @date 2024-07-02 14:07
 */

/**
 * 用户用于操作用户个人信息的相关接口
 */
@RestController
@RequestMapping("/userinfo")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 用户获取自己的个人信息接口
     * @param token 携带的用户信息
     * @return
     */
    @GetMapping("/getinfo")
    public Resp getUserInfo(@RequestHeader String token){
        return userInfoService.getUserInfo(token);
    }

    /**
     * 用户用于修改个人信息的接口，不修改密码
     * @param token 携带的用户信息
     * @param user  更新后的用户信息
     * @return
     */
    @PostMapping("/edit")
    public Resp editUserInfo(@RequestHeader String token,@RequestBody User user){
        return userInfoService.editUserInfo(token,user);
    }

    /**
     * 用户用于修改密码的接口
     * @param token 携带的个人信息
     * @return
     */
    @PostMapping("/resetpassword")
    public Resp resetPassword(@RequestHeader String token, @RequestBody ChangePasswordDTO dto){
        return userInfoService.resetPassword(token,dto.getOldPassword(),dto.getNewPassword());
    }

    /**
     * 用户头像上传的相关接口
     * @param token     用户携带的信息
     * @param file      要上传的头像文件（限制大小为2MB）
     * @return
     */
    @PostMapping("/upload/avatar")
    public Resp uploadAvatar(@RequestHeader String token, @RequestParam MultipartFile file){
        return userInfoService.uploadAvatar(token,file);
    }


}
