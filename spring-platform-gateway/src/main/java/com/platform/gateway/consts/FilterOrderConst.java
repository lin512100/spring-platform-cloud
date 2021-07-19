package com.platform.gateway.consts;

/**
 * 过滤器限制优先级
 * @author lin512100
 * @date 2021/7/19
 */
public class FilterOrderConst {

    /**
     * 黑名单地址
     * */
    public final static int PATH_FILTER = 99;

    /**
     *  token 校验
     * */
    public final static int TOKEN_FILTER = 100;

    /**
     * 黑名单
     * */
    public final static int BLACK_FILTER = 101;
}
