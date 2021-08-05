package com.platform.openfeign.service;

import com.platform.common.consts.SecurityConst;
import com.platform.model.vo.OauthUserVo;
import com.platform.openfeign.consts.ServiceConst;
import com.platform.openfeign.consts.UserServiceApiUrl;
import com.platform.openfeign.service.impl.UserApiServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import static com.platform.openfeign.consts.BasicServiceApiUrl.PRE_BASIC_SERVICE;
import static com.platform.openfeign.consts.UserServiceApiUrl.LOAD_USER_BY_USERNAME;
import static com.platform.openfeign.consts.UserServiceApiUrl.PRE_USER_SERVICE;

/**
 * 用户服务类
 * @author lin512100
 * @date 2021/6/29
 */
@FeignClient(value = ServiceConst.PLATFORM_USER,fallback = UserApiServiceImpl.class)
public interface UserApiService {

    /**
     * 获取用户信息
     * @param username      用户登录名
     * @param authorization 调用token
     * @return {@link OauthUserVo}
     */
    @GetMapping(PRE_USER_SERVICE + LOAD_USER_BY_USERNAME)
    OauthUserVo loadUserByUsername(@RequestParam("username") String username, @RequestHeader(SecurityConst.AUTHORIZATION) String authorization);
}
