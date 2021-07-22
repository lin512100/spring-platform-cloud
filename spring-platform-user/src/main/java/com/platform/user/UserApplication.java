package com.platform.user;

import com.platform.common.utils.BeanUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 账户服务
 * @author lin512100
 * @date 2021/6/29
 */
@EnableFeignClients("com.platform.openfeign.service")
@SpringBootApplication(scanBasePackages = "com.platform.**")
@MapperScan(basePackages = "com.platform.user.mapper")
public class UserApplication {

    public static void main(String[] args) {
        BeanUtils.applicationContext = SpringApplication.run(UserApplication.class);
    }
}
