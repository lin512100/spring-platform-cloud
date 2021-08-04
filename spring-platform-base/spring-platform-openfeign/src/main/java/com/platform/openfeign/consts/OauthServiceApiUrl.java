package com.platform.openfeign.consts;

/**
 * oauth授权服务接口
 * @author lin512100
 * @date 2021/6/29
 */
public class OauthServiceApiUrl {

    /**
     * 服务请求路径前缀
     */
    public static final String PRE_OAUTH_SERVICE = "/platform-oauth";

    /**
     * 获取登录信息
     */
    public final static String GET_SYSTEM_OAUTH_TOKEN = "/oauth/token";
}
