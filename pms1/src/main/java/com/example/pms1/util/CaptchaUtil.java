// This file is auto-generated, don't edit it. Thanks.
package com.example.pms1.util;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.models.*;
import com.aliyun.sdk.service.dysmsapi20170525.*;
import com.google.gson.Gson;
import darabonba.core.client.ClientOverrideConfiguration;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import javax.net.ssl.KeyManager;
//import javax.net.ssl.X509TrustManager;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Component
public class CaptchaUtil {
    @Autowired
    RedisCache redisCache;

    public void sendsms(String phoneNum) throws Exception {

        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId("xxx")
                .accessKeySecret("xxx")
                .build());

        // Configure the Client
        AsyncClient client = AsyncClient.builder()
                .region("cn-hangzhou") // Region ID
                .credentialsProvider(provider)
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride("dysmsapi.aliyuncs.com")
                )
                .build();

        String smscode;
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(10));
        }
        smscode = sb.toString();
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .signName("java实训")
                .templateCode("SMS_468885445")
                .phoneNumbers(phoneNum)
                .templateParam("{\"code\":"+smscode+"}")
                .build();

        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
        SendSmsResponse resp = response.get();
        System.out.println(new Gson().toJson(resp));
        client.close();
        redisCache.setCacheObject("captcha of "+phoneNum,smscode,120, TimeUnit.SECONDS);
    }

    public boolean checkCaptcha(String phone,String captcha){
        String oldcap = redisCache.getCacheObject("captcha of "+phone);
        if(Objects.isNull(oldcap)){
            throw new RuntimeException("用户未登录");
        }
        return oldcap.equals(captcha);
    }

    public static void main(String[] args) throws Exception {
//        CaptchaUtil captchaUtil = new CaptchaUtil();
//        captchaUtil.sendsms("13514995984");
//        Scanner scanner = new Scanner(System.in);
//        String captcha = scanner.next();
//        captchaUtil.checkCaptcha("13514995984",captcha);
//        System.out.println(new CaptchaUtil().redisCache);

    }


}