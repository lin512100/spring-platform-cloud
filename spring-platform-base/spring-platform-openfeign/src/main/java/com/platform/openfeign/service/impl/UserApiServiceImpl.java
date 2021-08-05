package com.platform.openfeign.service.impl;

import com.platform.model.vo.OauthUserVo;
import com.platform.openfeign.service.UserApiService;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户服务API容错降级
 * @author lin512100
 * @date 2021/8/5
 */
@Slf4j
public class UserApiServiceImpl implements UserApiService {

    @Override
    public OauthUserVo loadUserByUsername(String username, String authorization) {
        throw new RuntimeException("获取用户信息异常");
    }
}
