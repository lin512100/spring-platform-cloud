package com.platform.gateway.cache;

import com.platform.model.vo.basic.SysBlackRouteVo;

import java.util.List;

/**
 * 黑名单缓存
 * @author lin512100
 * @date 2021/8/3
 */
public interface BlackRouteCache {

    /**
     * 获取所有黑名单路由
     * */
    List<SysBlackRouteVo> getAllBlackRoute();
}
