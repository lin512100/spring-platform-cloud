package com.platform.gateway.filter;

import com.platform.gateway.consts.FilterOrderConst;
import com.platform.gateway.utils.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 黑名单过滤器
 * @author lin512100
 * @date 2021/7/19
 */
@Slf4j
@Component
public class BlackListFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 从request对象中获取客户端ip
        String clientIp = IPUtils.getIpAddress(request);
        log.info("IP 拦截：" + clientIp);


        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return FilterOrderConst.BLACK_FILTER;
    }
}
