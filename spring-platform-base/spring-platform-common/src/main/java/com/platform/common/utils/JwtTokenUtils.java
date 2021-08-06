package com.platform.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.Map;

/**
 * Token工具类
 * @author lin512100
 * @date 2021/8/6
 */
public class JwtTokenUtils {

    /** 证书文件 */
    private static InputStream INPUT_STREAM = Thread.currentThread().getContextClassLoader().getResourceAsStream("jwt.jks");
    private static PrivateKey privateKey = null;
    private static PublicKey publicKey = null;

    static { // 将证书文件里边的私钥公钥拿出来
        try {
            // java key store 固定常量
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(INPUT_STREAM, "123456".toCharArray());
            // jwt 为 命令生成整数文件时的别名
            privateKey = (PrivateKey) keyStore.getKey("jwt", "123456".toCharArray());
            publicKey = keyStore.getCertificate("jwt").getPublicKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成token
     * @param subject （主体信息）
     * @param expirationSeconds 过期时间（秒）
     * @param claims 自定义身份信息
     * @return String
     */
    public static String generateToken(String subject, int expirationSeconds, Map<String,Object> claims) {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setExpiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000))
//                .signWith(SignatureAlgorithm.HS512, salt) // 不使用公钥私钥
            .signWith(SignatureAlgorithm.RS256, privateKey)
            .compact();
    }

    /**
     * 解析token,获得subject中的信息
     * @param token token值
     * */
    public static String parseToken(String token, String salt) {
        String subject = null;
        try {
            subject = getTokenBody(token).getSubject();
        } catch (Exception ignored) {
        }
        return subject;
    }

    /** 获取token自定义属性 */
    public static Map<String,Object> getClaims(String token){
        Map<String,Object> claims = null;
        try {
            claims = getTokenBody(token);
        }catch (Exception ignored) {
        }

        return claims;
    }

    /**
     * 是否已过期
     *  */
    public static boolean isExpiration(String token){
        return getTokenBody(token).getExpiration().before(new Date());
    }

    private static Claims getTokenBody(String token){
        return Jwts.parser()
            .setSigningKey(publicKey)
            .parseClaimsJws(token)
            .getBody();
    }
}
