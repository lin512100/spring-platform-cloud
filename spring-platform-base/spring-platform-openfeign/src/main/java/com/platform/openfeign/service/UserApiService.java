package com.platform.openfeign.service;

import com.platform.model.vo.OauthUserVo;
import com.platform.openfeign.config.FeignOauth2RequestInterceptor;
import com.platform.openfeign.consts.ServiceConst;
import com.platform.openfeign.utils.FeignUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 用户服务类
 * @author lin512100
 * @date 2021/6/29
 */
@FeignClient(value = ServiceConst.PLATFORM_USER, configuration = FeignOauth2RequestInterceptor.class)
public interface UserApiService {

    /**
     * 获取用户信息
     * @param username      用户登录名
     * @param authorization 调用token
     * @return {@link OauthUserVo}
     */
    @GetMapping("/user/info/oauth")
    OauthUserVo loadUserByUsername(String username, @RequestHeader(FeignUtils.AUTHORIZATION) String authorization);
}
