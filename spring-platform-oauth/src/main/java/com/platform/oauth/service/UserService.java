package com.platform.oauth.service;

import com.platform.model.dto.oauth.AccountLoginDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

/**
 * 用户服务
 * @author lin512100
 * @date 2021/8/4
 */
public interface UserService {

    /**
     * 用户登录
     * @param dto 登录实体类
     * @param authentication 权限信息
     * @return Token
     *
     * */
    OAuth2AccessToken login(AccountLoginDto dto, Authentication authentication);
}
