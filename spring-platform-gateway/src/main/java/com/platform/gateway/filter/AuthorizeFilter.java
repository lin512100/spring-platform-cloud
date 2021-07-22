package com.platform.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.platform.gateway.consts.FilterOrderConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static com.platform.common.consts.SecurityConst.*;

/**
 * 自定义拦截器
 * @author lin512100
 * @date 7/18/2021
 */
@Component
@Slf4j
public class AuthorizeFilter implements GlobalFilter, Ordered {

    private final static String USER_NAME = "user_name";

    @Resource
    private TokenStore tokenStore;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestUrl = exchange.getRequest().getPath().value();
        AntPathMatcher pathMatcher = new AntPathMatcher();


        //2.检查token是否存在
        String token = getToken(exchange);
        if(StringUtils.isEmpty(token)){
           return noTokenMono(exchange);
        }
        //3.判断是不是有效的token
        OAuth2AccessToken oAuth2AccessToken;
        try {
            oAuth2AccessToken = tokenStore.readAccessToken(token);
            Map<String, Object> additionalInformation = oAuth2AccessToken.getAdditionalInformation();
            //取出用户身份信息
            String principal  = String.valueOf(additionalInformation.get(USER_NAME));
            //获取用户的权限
            Object authorities = additionalInformation.get(AUTHORITIES);

            JSONObject jsonObject=new JSONObject();
            jsonObject.put(PRINCIPAL,principal);
            jsonObject.put(AUTHORITIES,authorities);
            //给header里面添加值
            //String encode = new BCryptPasswordEncoder().encode(jsonObject.toJSONString());  //加密
            ServerHttpRequest tokenRequest = exchange.getRequest().mutate().header(ACCESS_TOKEN, jsonObject.toJSONString()).build();
            ServerWebExchange build = exchange.mutate().request(tokenRequest).build();
            return chain.filter(build);
        }catch (InvalidTokenException e){
            log.info("无效的token: {}", token);
            return invalidTokenMono(exchange);
        }

    }

    @Override
    public int getOrder() {
        return FilterOrderConst.TOKEN_FILTER;
    }

    /**
     * 获取token
     */
    private String getToken(ServerWebExchange exchange){
        List<String> authorizations = exchange.getRequest().getHeaders().get(AUTHORIZATION);
        if(CollectionUtils.isEmpty(authorizations)){
            return null;
        }
        String token = authorizations.get(0);
        return token.replace(PRE_AUTHORIZATION,"");
    }

    /**
     * 没有token
     */
    private Mono<Void> noTokenMono(ServerWebExchange exchange){
        JSONObject json = new JSONObject();
        json.put("status", HttpStatus.UNAUTHORIZED);
        json.put("data","权限不足");
        return jsonToMono(json,exchange);
    }

    /**
     * 将json字符串转换为Mono的格式,并添加到相应中去
     * @param json JSON数据
     */
    private Mono<Void> jsonToMono(JSONObject json, ServerWebExchange exchange){
        ServerHttpResponse response = exchange.getResponse();
        byte[] bytes = json.toJSONString().getBytes(StandardCharsets.UTF_8);
        DataBuffer dataBuffer = response.bufferFactory().wrap(bytes);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
        return response.writeWith(Mono.just(dataBuffer));
    }

    /**
     * 无效的token
     */
    private Mono<Void> invalidTokenMono(ServerWebExchange exchange){
        JSONObject json = new JSONObject();
        json.put("status",HttpStatus.UNAUTHORIZED);
        json.put("data", "无效的token");
        return jsonToMono(json, exchange);
    }

}
