package com.platform.web.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.platform.web.handler.MybatisDataInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;


/**
 * 网页全局配置
 * @author lin512100
 * @date 6/14/2021
 */
@Configuration
public class MyBatisConfig {

    @Resource
    private MybatisDataInterceptor mybatisDataInterceptor;

    /**
     * MyBatis分页插件
     */
    @Bean
    @ConditionalOnMissingBean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        interceptor.addInnerInterceptor(mybatisDataInterceptor);
        return interceptor;
    }
}
