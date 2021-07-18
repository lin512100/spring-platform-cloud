package com.platform.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * WebFlux安全配置
 * @author lin512100
 * @date 7/18/2021
 */
@Configuration
@EnableWebFluxSecurity
public class WebFluxSecurityConfig {

    /**
     * 判断某个请求是否匹配该安全过滤器链 – boolean matches(HttpServletRequest request)
     * 获取该安全过滤器链所对应的安全过滤器 – List<Filter> getFilters()
     */
    @Bean
    public SecurityWebFilterChain webFilterChain(ServerHttpSecurity http){
        return http
                .authorizeExchange()
                .pathMatchers("/**").permitAll()
                .anyExchange().authenticated()
                .and().csrf().disable().build();
    }

}
