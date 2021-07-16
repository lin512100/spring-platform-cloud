package com.platform.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Oauth认证服务器
 * @author lin512100
 * @date 6/27/2021
 */
@EnableFeignClients(basePackages = "com.platform.openfeign.service")
@SpringBootApplication(scanBasePackages = "com.platform.**")
public class OauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthApplication.class);
    }
}
