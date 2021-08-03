package com.platform.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.platform.gateway.cache.BlackRouteCache;
import com.platform.gateway.cache.WhiteRouteCache;
import com.platform.gateway.consts.FilterOrderConst;
import com.platform.gateway.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * 黑名单过滤器
 * @author lin512100
 * @date 2021/7/19
 */
@Slf4j
@Component
public class BlackRouteFilter implements GlobalFilter, Ordered {

    @Resource
    private BlackRouteCache blackRouteCache;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestUrl = exchange.getRequest().getPath().value();
        // 拦截黑名单
        if (blackRouteCache.matcherUrl(requestUrl)) {
            JSONObject json = new JSONObject();
            json.put("status", HttpStatus.UNAUTHORIZED);
            json.put("data", "权限不足");
            return jsonToMono(json, exchange);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return FilterOrderConst.BLACK_FILTER;
    }

    /**
     * 将json字符串转换为Mono的格式,并添加到相应中去
     * @param json JSON数据
     */
    private Mono<Void> jsonToMono(JSONObject json, ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        byte[] bytes = json.toJSONString().getBytes(StandardCharsets.UTF_8);
        DataBuffer dataBuffer = response.bufferFactory().wrap(bytes);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
        return response.writeWith(Mono.just(dataBuffer));
    }
}
