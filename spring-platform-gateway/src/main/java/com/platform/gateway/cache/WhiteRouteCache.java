package com.platform.gateway.cache;

import com.platform.model.vo.basic.SysWhiteRouteVo;

import java.util.List;

/**
 * 白名单缓存
 * @author lin512100
 * @date 2021/8/3
 */
public interface WhiteRouteCache {

    /**
     * 获取所有白名单列表
     * */
    List<SysWhiteRouteVo> getAllWhiteRoute();
}
