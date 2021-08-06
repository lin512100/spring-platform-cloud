package com.platform.security.config;

import com.alibaba.fastjson.JSONObject;
import com.platform.common.consts.SecurityConst;
import com.platform.model.vo.UserInfo;
import com.platform.security.vo.SysUserVO;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;

/**
 * JwtAccessToken转换器
 * @author lin512100
 * @date 2021/8/6
 */
public class JwtAccessToken extends JwtAccessTokenConverter {

    /**
     * 生成token
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken oAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
        if (authentication.getDetails() != null) {
            // 设置额外用户信息
            UserInfo userInfo = ((SysUserVO) authentication.getDetails()).getUserInfo();
            // 将用户信息添加到token额外信息中
            oAuth2AccessToken.getAdditionalInformation().put(SecurityConst.JWT_USER_INFO, userInfo);
        }
        return super.enhance(oAuth2AccessToken, authentication);
    }

    /**
     * 解析token
     */
    @Override
    public OAuth2AccessToken extractAccessToken(String value, Map<String, ?> map) {
        OAuth2AccessToken oauth2AccessToken = super.extractAccessToken(value, map);
        convertData(oauth2AccessToken, oauth2AccessToken.getAdditionalInformation());
        return oauth2AccessToken;
    }

    private void convertData(OAuth2AccessToken accessToken, Map<String, ?> map) {
        if (accessToken.getAdditionalInformation().containsKey(SecurityConst.JWT_USER_INFO)) {
            accessToken.getAdditionalInformation().put(SecurityConst.JWT_USER_INFO, convertUserData(map.get(SecurityConst.JWT_USER_INFO)));
        }
    }

    private UserInfo convertUserData(Object map) {
        return JSONObject.parseObject(JSONObject.toJSONString(map), UserInfo.class);
    }
}
