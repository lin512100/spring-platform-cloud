package com.platform.gateway.cache;

import com.platform.model.vo.basic.SysWhiteRouteVo;
import org.springframework.util.AntPathMatcher;

import java.util.List;

/**
 * 白名单缓存
 * @author lin512100
 * @date 2021/8/3
 */
public interface WhiteRouteCache {

    /**
     * 获取所有白名单列表
     * @return list
     */
    List<SysWhiteRouteVo> getAllWhiteRoute();

    /**
     * 路由匹配
     * @param url 路由链接
     * @return boolean 满足(true)
     */
    default boolean matcherUrl(String url) {
        SysWhiteRouteVo whiteRouteVo = getAllWhiteRoute().stream().filter(item -> {
            AntPathMatcher matcher = new AntPathMatcher();
            return matcher.match(item.getWhiteUrl(), url);
        }).findFirst().orElse(null);
        return whiteRouteVo != null;
    }
}
