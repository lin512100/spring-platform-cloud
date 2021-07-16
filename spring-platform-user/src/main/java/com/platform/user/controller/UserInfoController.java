package com.platform.user.controller;

import com.platform.model.vo.OauthUserVo;
import com.platform.openfeign.service.OauthApiService;
import com.platform.openfeign.utils.FeignUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private OauthApiService oauthApiService;

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

    @GetMapping("/aa")
    public String aaaa(){
        String sysToken = FeignUtils.getSysToken(oauthApiService.getServiceToken(FeignUtils.getTokenMap("Admin","Admin")));
        return sysToken;
    }

}
