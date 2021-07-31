package com.platform.user.controller;

import com.platform.model.vo.OauthUserVo;
import com.platform.user.service.SysAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;

import static com.platform.openfeign.consts.UserServiceApiUrl.LOAD_USER_BY_USERNAME;

/**
 *
 * @author lin512100
 * @date 2021/7/22
 */
@RestController
@RequestMapping
@Api(tags = "API接口-用户模块")
public class UserApiController {

    @Resource
    private SysAccountService accountService;

    @ApiOperation(value = "获取用户信息")
    @GetMapping(LOAD_USER_BY_USERNAME)
    public OauthUserVo loadUserByUsername(String username) {
        return accountService.getOauthUserVo(username);
    }
}
