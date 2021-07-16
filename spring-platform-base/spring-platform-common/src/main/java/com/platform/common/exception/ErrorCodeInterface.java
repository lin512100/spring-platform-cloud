package com.platform.common.exception;

/**
 * @author lin512100
 * @date 2021/5/17
 */
public interface ErrorCodeInterface {

    /**
     * 获取错误码
     * @return code
     */
    Integer getCode();

    /**
     * 获取信息
     * @return msg
     */
    String getMsg();
}
