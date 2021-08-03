package com.platform.openfeign.service.impl;

import com.platform.model.vo.basic.SysBlackRouteVo;
import com.platform.model.vo.basic.SysDictAllVo;
import com.platform.model.vo.basic.SysWhiteRouteVo;
import com.platform.openfeign.service.BasicApiService;

import java.util.List;

/**
 * 容错降级
 * @author lin512100
 * @date 2021/7/22
 */
public class BasicApiServiceImpl implements BasicApiService {

    @Override
    public List<SysDictAllVo> getAllDict(String authorization) {
        throw new RuntimeException("字典信息获取失败");

    }

    @Override
    public List<SysBlackRouteVo> getAllBlackRoute(String authorization) {
        throw new RuntimeException("黑名单路由获取失败");
    }

    @Override
    public List<SysWhiteRouteVo> getAllWhiteRoute(String authorization) {
        throw new RuntimeException("白名单路由获取失败");
    }
}
