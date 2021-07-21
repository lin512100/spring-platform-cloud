package com.platform.openfeign.service;

import com.platform.common.consts.SecurityConst;
import com.platform.model.vo.OauthUserVo;
import com.platform.openfeign.consts.ServiceConst;
import com.platform.openfeign.consts.UserServiceApiUrl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户服务类
 * @author lin512100
 * @date 2021/6/29
 */
@FeignClient(value = ServiceConst.PLATFORM_USER)
public interface UserApiService {

    /**
     * 获取用户信息
     * @param username      用户登录名
     * @param authorization 调用token
     * @return {@link OauthUserVo}
     */
    @GetMapping(UserServiceApiUrl.LOAD_USER_BY_USERNAME)
    OauthUserVo loadUserByUsername(@RequestParam("username") String username, @RequestHeader(SecurityConst.AUTHORIZATION) String authorization);
}
