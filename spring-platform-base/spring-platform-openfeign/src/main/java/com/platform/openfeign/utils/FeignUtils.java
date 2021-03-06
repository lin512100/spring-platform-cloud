package com.platform.openfeign.utils;

import com.platform.common.consts.SecurityConst;
import com.platform.common.exception.SystemErrorCode;
import com.platform.common.exception.SystemException;
import com.platform.common.utils.SpringBeanUtils;
import com.platform.openfeign.properties.FeignProperties;
import com.platform.openfeign.service.OauthApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static com.platform.common.consts.SecurityConst.PRE_AUTHORIZATION;

/**
 * 获取token工具类
 * @author lin512100
 * @date 2021/6/29
 */
public class FeignUtils {

    /**
     * 获取系统Token
     */
    public static String getInnerToken(){
        FeignProperties feignProperties = SpringBeanUtils.getBean(FeignProperties.class);
        if(StringUtils.isEmpty(feignProperties.getClientId()) ||  StringUtils.isEmpty(feignProperties.getClientSecret())){
            throw new RuntimeException("客户端信息未配置");
        }
        Map<String, String> map = new HashMap<>(3);
        map.put("client_id", feignProperties.getClientId());
        map.put("client_secret", feignProperties.getClientSecret());
        map.put("grant_type", "client_credentials");
        ResponseEntity<OAuth2AccessToken> accessToken = SpringBeanUtils.getBean(OauthApiService.class).getServiceToken(map);
        if (!accessToken.getStatusCode().equals(HttpStatus.OK)) {
            throw new SystemException(SystemErrorCode.DATA_ERROR_NONE, "获取系统调用Token异常");
        }
        if (accessToken.getBody() == null) {
            throw new SystemException(SystemErrorCode.DATA_ERROR_NONE, "获取系统调用Token异常");
        }
        return PRE_AUTHORIZATION +  accessToken.getBody().getValue();
    }
}
