package com.platform.openfeign.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Feign 内部属性
 * @author lin512100
 * @date 2021/7/21
 */
@Getter
@Setter
@Lazy
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "customer.feign")
public class FeignProperties {

    /** 客户端Id*/
    private String clientId;

    /** 客户端秘钥 */
    private String clientSecret;
}
