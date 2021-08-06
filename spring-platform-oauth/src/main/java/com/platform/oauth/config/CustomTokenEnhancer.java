package com.platform.oauth.config;

import com.alibaba.fastjson.JSONObject;
import com.platform.oauth.vo.SysUserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义JWT信息
 * @author lin512100
 * @date 2021/8/6
 */
@Slf4j
public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = new HashMap<>(2);
        try{
            SysUserVO userVO = (SysUserVO) authentication.getPrincipal();
            additionalInfo.put("aa", JSONObject.toJSONString(userVO));
        }catch (Exception ignored){}

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
