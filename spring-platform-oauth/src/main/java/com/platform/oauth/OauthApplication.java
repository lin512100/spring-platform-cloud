package com.platform.oauth;

import com.platform.common.utils.BeanUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Oauth认证服务器
 * @author lin512100
 * @date 6/27/2021
 */
@EnableFeignClients("com.platform.openfeign.service")
@SpringBootApplication(scanBasePackages = "com.platform.**")
@MapperScan(basePackages = "com.platform.oauth.mapper")
public class OauthApplication {

    public static void main(String[] args) {
        // 设置静态变量 供全局使用
        BeanUtils.applicationContext = SpringApplication.run(OauthApplication.class);
    }
}
