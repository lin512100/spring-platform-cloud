package com.platform.user.controller;

import com.platform.model.vo.OauthUserVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class SysUserApiController {

    /**
     * 获取用户信息
     * @param username 用户名
     * @return {@link OauthUserVo}
     */
    @GetMapping(LOAD_USER_BY_USERNAME)
    public OauthUserVo loadUserByUsername(String username) {
        OauthUserVo oauthUserVo = new OauthUserVo();
        oauthUserVo.setUsername(username);
        oauthUserVo.setPassword("$2a$10$m7bX9siCoqz6LrcMa4pLVe0yI6St6..kOBWHHN23wuPmqx8jurKy2");
        oauthUserVo.setGrantedAuthorityList(Collections.singletonList("Admin"));
        return oauthUserVo;
    }
}
