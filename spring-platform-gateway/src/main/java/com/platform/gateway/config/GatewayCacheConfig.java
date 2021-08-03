package com.platform.gateway.config;

import com.platform.gateway.cache.BlackRouteCache;
import com.platform.gateway.cache.InMemoryBlackRouteCache;
import com.platform.gateway.cache.InMemoryWhiteRouteCache;
import com.platform.gateway.cache.WhiteRouteCache;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * 缓存实现类
 * @author lin512100
 * @date 2021/8/3
 */
@Configuration
public class GatewayCacheConfig {

    /**
     * 黑名单默认配置
     */
    @Bean
    @Lazy
    @ConditionalOnMissingBean(BlackRouteCache.class)
    public BlackRouteCache blackRouteCache() {
        return new InMemoryBlackRouteCache();
    }

    /**
     * 白名单默认配置
     */
    @Bean
    @Lazy
    @ConditionalOnMissingBean(WhiteRouteCache.class)
    public WhiteRouteCache whiteRouteCache() {
        return new InMemoryWhiteRouteCache();
    }
}
