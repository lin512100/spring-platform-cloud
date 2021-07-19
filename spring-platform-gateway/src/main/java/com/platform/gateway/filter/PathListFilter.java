package com.platform.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static com.platform.gateway.consts.FilterOrderConst.PATH_FILTER;

/**
 * 静止访问地址
 * @author lin512100
 * @date 2021/7/19
 */
@Slf4j
@Component
public class PathListFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestUrl = exchange.getRequest().getPath().value();


        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return PATH_FILTER;
    }
}
