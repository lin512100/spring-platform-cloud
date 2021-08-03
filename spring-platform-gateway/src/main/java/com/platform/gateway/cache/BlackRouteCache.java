package com.platform.gateway.cache;

import com.platform.model.vo.basic.SysBlackRouteVo;
import org.springframework.util.AntPathMatcher;

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

    /**
     * 路由匹配
     * @param url 路由链接
     * @return boolean 满足(true)
     */
    default boolean matcherUrl(String url) {
        SysBlackRouteVo whiteRouteVo = getAllBlackRoute().stream().filter(item -> {
            AntPathMatcher matcher = new AntPathMatcher();
            return matcher.match(item.getBlackUrl(), url);
        }).findFirst().orElse(null);
        return whiteRouteVo != null;
    }
}
