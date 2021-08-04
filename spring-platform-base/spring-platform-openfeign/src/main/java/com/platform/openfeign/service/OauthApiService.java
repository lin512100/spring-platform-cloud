package com.platform.openfeign.service;

import com.platform.openfeign.consts.OauthServiceApiUrl;
import com.platform.openfeign.consts.ServiceConst;
import com.platform.openfeign.service.impl.OauthApiServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

import static com.platform.openfeign.consts.OauthServiceApiUrl.GET_SYSTEM_OAUTH_TOKEN;
import static com.platform.openfeign.consts.OauthServiceApiUrl.PRE_OAUTH_SERVICE;


/**
 * Oauth服务类
 * @author lin512100
 * @date 2021/6/29
 */
@FeignClient(value = ServiceConst.PLATFORM_OAUTH, fallback = OauthApiServiceImpl.class)
public interface OauthApiService {

    /**
     * 授权模式获取Token
     * @param parameters 授权信息
     * @return String
     */
    @PostMapping(PRE_OAUTH_SERVICE + GET_SYSTEM_OAUTH_TOKEN)
    ResponseEntity<OAuth2AccessToken> getServiceToken(@RequestParam Map<String, String> parameters);
}
