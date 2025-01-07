package com.example.pms1.service;

import com.example.pms1.dto.Resp;
import com.example.pms1.entity.User;

/**
 * @author loself
 * @date 2024-07-02 11:13
 */
public interface RegisterService  {

    Resp register(User user,String captcha);
}
