package com.platform.oauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Oauth认证服务器
 * @author lin512100
 * @date 6/27/2021
 */
@SpringBootApplication
@MapperScan(basePackages = "com.platform.oauth.mapper")
public class OauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthApplication.class);
    }
}
