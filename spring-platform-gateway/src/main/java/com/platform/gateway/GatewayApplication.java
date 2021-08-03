package com.platform.gateway;

import com.platform.common.utils.SpringBeanUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * 网关服务
 * @author lin512100
 * @date 7/16/2021
 */
@EnableOpenApi
@EnableScheduling
@EnableDiscoveryClient
@EnableFeignClients("com.platform.openfeign.service")
@SpringBootApplication(scanBasePackages = "com.platform.**")
public class GatewayApplication {

    public static void main(String[] args) {
        SpringBeanUtils.applicationContext = SpringApplication.run(GatewayApplication.class);
    }
}
