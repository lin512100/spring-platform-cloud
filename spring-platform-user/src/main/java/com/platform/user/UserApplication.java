package com.platform.user;

import com.platform.common.utils.SpringBeanUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 账户服务
 * @author lin512100
 * @date 2021/6/29
 */
@EnableOpenApi
@EnableFeignClients("com.platform.openfeign.service")
@SpringBootApplication(scanBasePackages = "com.platform.**")
@MapperScan(basePackages = "com.platform.user.mapper")
public class UserApplication {

    public static void main(String[] args) {
        SpringBeanUtils.applicationContext = SpringApplication.run(UserApplication.class);
    }
}
