package com.platform.user.controller;

import com.platform.model.vo.OauthUserVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * 用户信息控制器
 * @author lin512100
 * @date 2021/6/29
 */
@RestController
@RequestMapping("/")
public class UserInfoController {

    /**
     * 获取用户信息
     * @param username 用户名
     * @return {@link OauthUserVo}
     */
    @GetMapping("info/oauth")
    public OauthUserVo loadUserByUsername(String username) {
        OauthUserVo oauthUserVo = new OauthUserVo();
        oauthUserVo.setUsername(username);
        oauthUserVo.setPassword("$2a$10$m7bX9siCoqz6LrcMa4pLVe0yI6St6..kOBWHHN23wuPmqx8jurKy2");
        oauthUserVo.setGrantedAuthorityList(Collections.singletonList("Admin"));
        return oauthUserVo;
    }


}
