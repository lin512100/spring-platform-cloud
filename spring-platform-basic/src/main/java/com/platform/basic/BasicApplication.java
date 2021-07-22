package com.platform.basic;

import com.platform.common.utils.BeanUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 底层服务
 * @author lin512100
 * @date 2021/7/22
 */
@EnableFeignClients("com.platform.openfeign.service")
@SpringBootApplication(scanBasePackages = "com.platform.**")
@MapperScan(basePackages = "com.platform.basic.mapper")
public class BasicApplication {

    public static void main(String[] args) {
        BeanUtils.applicationContext = SpringApplication.run(BasicApplication.class);
    }
}
