package com.platform.openfeign.service.impl;

import com.platform.openfeign.service.OauthApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 权限异常错误实现类
 * @author lin512100
 * @date 2021/6/29
 */
public class OauthApiServiceImpl implements OauthApiService {

    @Override
    public ResponseEntity<OAuth2AccessToken> getServiceToken(Map<String,String> parameters) {
        throw new RuntimeException("内部Token 获取异常");
    }
}
