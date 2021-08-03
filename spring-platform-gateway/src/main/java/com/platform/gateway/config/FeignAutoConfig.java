package com.platform.gateway.config;

import feign.codec.Decoder;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class FeignAutoConfig {

    /**
     * gateway使用feign请求的时候报HttpMessageConverters未注入异常,
     * 自行配置一个Decoder, 避免该异常
     */
    @Bean
    public Decoder feignDecoder() {
        final HttpMessageConverters httpMessageConverters = new HttpMessageConverters(new MappingJackson2HttpMessageConverter());
        return new ResponseEntityDecoder(new SpringDecoder(() -> httpMessageConverters));
    }
}
