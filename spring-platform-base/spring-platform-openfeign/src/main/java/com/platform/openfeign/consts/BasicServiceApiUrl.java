package com.platform.openfeign.consts;

/**
 * 底层服务接口
 * @author lin512100
 * @date 2021/6/29
 */
public class BasicServiceApiUrl {

    /**
     * 服务请求路径前缀
     */
    public static final String PRE_BASIC_SERVICE = "/platform-basic";

    /**
     * 获取所有字典信息
     */
    public static final String GET_ALL_DICT = "/getAllDict";

    /**
     * 获取所有黑名单信息
     */
    public static final String GET_ALL_BLACK_ROUTE = "/getAllBlackRoute";

    /**
     * 获取所有白名单信息
     */
    public static final String GET_ALL_WHITE_ROUTE = "/getAllWhiteRoute";
}
