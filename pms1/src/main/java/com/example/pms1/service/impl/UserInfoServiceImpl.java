package com.example.pms1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.pms1.dto.HttpStatus;
import com.example.pms1.dto.Resp;
import com.example.pms1.entity.User;
import com.example.pms1.service.UserInfoService;
import com.example.pms1.service.UserService;
import com.example.pms1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author loself
 * @date 2024-07-02 16:58
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private static final List<String> ALLOWED_CONTENT_TYPES = Arrays.asList(
            "image/jpeg",
            "image/png",
            "image/gif",
            "image/jpg"
    );

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Resp getUserInfo(String token) {
        try {
            String username = JwtUtil.parseJWT(token).getSubject();
            User user = userService.selectUserByUsername(username);
            return Resp.success(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resp editUserInfo(String token, User user) {
        try {
            User user1 = userService.selectUserByUsername(JwtUtil.parseJWT(token).getSubject());
            if(!user1.getUserId().equals(user.getUserId())){
                if(!Objects.isNull(userService.selectUserByUsername(user.getUsername()))){
                    return Resp.error(HttpStatus.REGISTER_USEREXISTS);
                }
            }
            Integer userId = user1.getUserId();
            user.setUpdatedAt(new Timestamp(new Date().getTime()));
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id",userId);
            boolean res = userService.update(user,queryWrapper);
            if(res){
                return Resp.success("修改成功");
            }else {
                return Resp.error(HttpStatus.MODIFY_ERROR);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Resp resetPassword(String token, String password, String newpassword) {
        try {
            User user = userService.selectUserByUsername(JwtUtil.parseJWT(token).getSubject());
            boolean res = passwordEncoder.matches(password, user.getPasswordHash());
            if(res){
                user.setPasswordHash(passwordEncoder.encode(newpassword));
                boolean update = userService.updateById(user);
                if(update){
                    return Resp.success("密码修改成功");
                }else {
                    return Resp.error(HttpStatus.ERROR);
                }
            }else {
                return Resp.error(HttpStatus.ORIGIN_WRONG);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resp uploadAvatar(String token, MultipartFile file) {
        String contentType = file.getContentType();
        if (!ALLOWED_CONTENT_TYPES.contains(contentType)){
            return Resp.error(HttpStatus.AVATAR_ERRORTYPE);
        }
        long maxFileSize=2*1024*1024;
        if(file.getSize()>maxFileSize){
            return Resp.error(HttpStatus.AVATAR_SIZELIMITS);
        }
        String upLoadDir=System.getProperty("user.dir")+File.separator+"avatars";
        File uploadDirFile = new File(upLoadDir);
        if(!uploadDirFile.exists()){
            uploadDirFile.mkdirs();
        }
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFilename = UUID.randomUUID().toString() + fileExtension;
        File destinationFile = new File(upLoadDir +File.separator+ newFilename);
        try {
            file.transferTo(destinationFile);
            String username = JwtUtil.parseJWT(token).getSubject();
            User user = userService.selectUserByUsername(username);
            if(Objects.isNull(user)){
                return Resp.error(HttpStatus.ERROR);
            }
            user.setAvatarUrl(newFilename);
            boolean res = userService.updateById(user);
            return res?Resp.success("头像上传成功"):Resp.error(HttpStatus.ERROR);
        }  catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
