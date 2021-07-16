package com.platform.openfeign.utils;

import com.platform.common.exception.SystemErrorCode;
import com.platform.common.exception.SystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取token工具类
 * @author lin512100
 * @date 2021/6/29
 */
public class FeignUtils {

    /**
     * 认证前缀
     */
    public final static String PRE_AUTH = "Bearer ";

    /**
     * 认证头
     */
    public final static String AUTHORIZATION = "Authorization";

    /**
     * 获取用户自带的Token
     */
    public static String getUserToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new SystemException(SystemErrorCode.DATA_ERROR_NONE, "获取用户Token异常");
        }
        AbstractAuthenticationToken token = (AbstractAuthenticationToken) authentication;
        if (token.getDetails() == null) {
            throw new SystemException(SystemErrorCode.DATA_ERROR_NONE, "获取用户Token异常");
        }
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) token.getDetails();
        return details.getTokenValue();
    }

    /**
     * 格式化用户token
     * @return 格式化Token
     */
    public static String userToken() {
        return PRE_AUTH + getUserToken();
    }

    /**
     * 格式化系统token参数
     * @return 格式化Token
     */
    public static String sysToken(ResponseEntity<OAuth2AccessToken> accessToken) {
        System.out.println("系统token:" +PRE_AUTH + getSysToken(accessToken));

        return PRE_AUTH + getSysToken(accessToken);
    }

    /**
     * 获取系统Token
     */
    public static String getSysToken(ResponseEntity<OAuth2AccessToken> accessToken) {
        if (!accessToken.getStatusCode().equals(HttpStatus.OK)) {
            throw new SystemException(SystemErrorCode.DATA_ERROR_NONE, "获取系统调用Token异常");
        }
        if (accessToken.getBody() == null) {
            throw new SystemException(SystemErrorCode.DATA_ERROR_NONE, "获取系统调用Token异常");
        }
        return accessToken.getBody().getValue();
    }

    /**
     * 获取内部系统Token参数
     */
    public static Map<String, String> getTokenMap(String clientId, String clientSecret) {
        Map<String, String> map = new HashMap<>();
        map.put("client_id", clientId);
        map.put("client_secret", clientSecret);
        map.put("grant_type", "client_credentials");
        return map;
    }
}
