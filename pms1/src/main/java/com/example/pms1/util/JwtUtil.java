package com.example.pms1.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
/**
 * @author loself
 * @date 2024-07-01 9:47
 */
public class JwtUtil {

    public static final Long JWT_TTL = 60 * 60 * 1000L; // 一个小时
    public static final String JWT_KEY = "xulei123";

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());// 设置过期时间
        return builder.compact();
    }
    public static String createJWT(String subject,Long ttlMillis) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        ttlMillis = ttlMillis == null ? JWT_TTL : ttlMillis;
        Date expDate = new Date(nowMillis + ttlMillis);
        SecretKey secretKey = generalKey();

        return Jwts.builder()
                .setId(getUUID())
                .setSubject(subject)
                .setIssuer("xl")
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setExpiration(expDate)
                .compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)              //唯一的ID
                .setSubject(subject)   // 主题  可以是JSON数据
                .setIssuer("xl")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey)
                .setExpiration(expDate);
    }
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    private static SecretKey generalKey() {
        byte[] encodedKey = Base64.getEncoder().encode(JWT_KEY.getBytes());
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "HmacSHA256");
    }

    //    public static void main(String[] args) throws Exception {
//        // 示例：创建和解析JWT
//        String jwt = createJWT("exampleSubject", null);
//        System.out.println("JWT: " + jwt);
//
//        Claims claims = parseJWT(jwt);
//        System.out.println("Subject: " + claims.getSubject());
//    }
    public static void main(String[] args) throws Exception {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJmMWZmZTk3YjVlOTM0NjM3OTJjOWJlNTlmYTRmYWRiYyIsInN1YiI6ImV4YW1wbGVTdWJqZWN0IiwiaXNzIjoieGwiLCJpYXQiOjE3MTk0MTEzNjIsImV4cCI6MTcxOTQxNDk2Mn0.rWeh_ff9Fb2WSOancWyibMZIxGsHXWTdg0vrc_YAWGY"
                ;
        Claims claims = parseJWT(token);
        String subject = claims.getSubject();
        System.out.println(subject);
    }
}
