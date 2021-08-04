package com.platform.oauth.controller;

import com.platform.common.response.ResultData;
import com.platform.model.dto.oauth.AccountLoginDto;
import com.platform.oauth.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登录授权
 * @author lin512100
 * @since 2021-07-21
 */
@RestController
@Api(tags = "用户登录授权模块")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "用户信息登录")
    public ResultData<OAuth2AccessToken> add(@RequestBody AccountLoginDto dto, Authentication authentication) {
        return ResultData.success(userService.login(dto, authentication));
    }

}
