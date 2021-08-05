package com.platform.oauth.service.impl;

import com.platform.common.utils.ValidateUtils;
import com.platform.model.dto.oauth.AccountLoginDto;
import com.platform.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * 用户服务类
 * @author lin512100
 * @date 2021/8/4
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices tokenServices;

    @Override
    public OAuth2AccessToken login(AccountLoginDto dto, Authentication authentication) {
        ValidateUtils.noEmpty(dto.getAccName(), "登录账户名");
        ValidateUtils.noEmpty(dto.getChannel(), "登录渠道");
        ValidateUtils.noEmpty(dto.getClientId(), "客户端ID");
        ValidateUtils.noEmpty(dto.getClientId(), "客户端秘钥");

        UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getAccName());

        ValidateUtils.isTrue(!userDetails.getPassword().equals(dto.getAccPwd()), "账户或密码输入错误");

        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(dto.getClientId());
        ValidateUtils.isTrue(clientDetails == null, "客户端信息不存在");
        ValidateUtils.isTrue(clientDetails.getClientSecret().equals(dto.getClientSecret()), "客户端秘钥不正确");

        // 创建TokenRequest
        TokenRequest tokenRequest = new TokenRequest(new HashMap<>(), dto.getClientId(), clientDetails.getScope(), "password");

        // 构建OAuth2Request
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);

        // 构建OAuth2Authentication
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);

        // 构建OAuth2AccessToken
        return tokenServices.createAccessToken(oAuth2Authentication);
    }
}
