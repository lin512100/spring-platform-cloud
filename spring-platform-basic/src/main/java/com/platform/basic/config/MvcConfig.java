package com.platform.basic.config;

import com.platform.basic.file.property.FileProperty;
import com.platform.common.utils.SpringBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MVC配置
 * @author lin512100
 * @date 2021/7/28
 */
@Configuration
public class MvcConfig {

    @Autowired
    private FileProperty fileProperty;

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/uploads/**").addResourceLocations(String.format("file:%s",
                    fileProperty.getFileBaseUrl()));
            }
        };
    }
}
