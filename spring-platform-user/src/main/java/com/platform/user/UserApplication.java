package com.platform.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 账户服务
 * @author lin512100
 * @date 2021/6/29
 */
@SpringBootApplication(scanBasePackages = "com.platform.**")
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class);
    }
}
