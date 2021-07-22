package com.platform.cache.config;

import com.platform.cache.dict.LocalDictCache;
import com.platform.cache.dict.DictCache;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 缓存配置
 * @author lin512100
 * @date 2021/7/22
 */
@Configuration
public class CacheConfig {

    @Bean
    @ConditionalOnMissingBean
    public DictCache dictCache(){
        return new LocalDictCache();
    }
}
