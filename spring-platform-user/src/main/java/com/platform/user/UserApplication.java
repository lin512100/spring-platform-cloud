package com.platform.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 账户服务
 * @author lin512100
 * @date 2021/6/29
 */
@EnableFeignClients(basePackages = "com.platform.openfeign.service")
@SpringBootApplication(scanBasePackages = "com.platform.**")
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class);
    }
}
