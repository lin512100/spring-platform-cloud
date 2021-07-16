package com.platform.common.exception;

/**
 * 系统异常枚举
 * @author lin512100
 * @date 2021/6/17
 */
public enum SystemErrorCode implements ErrorCodeInterface {


    /**
     * 系统类异常
     */
    UNKNOWN_EXCEPTION(1002, "%s"),
    ILLEGAL_ACCESS_EXCEPTION(1003, "反射异常"),
    INSTANTIATION_EXCEPTION(1004, "实例化异常"),

    /**
     * 参数类异常
     */
    PARAM_ERROR_NONE(1000, "%s参数不能为空"),

    PARAM_ERROR(3000, "%s参数异常"),

    /**
     * 数据类 异常
     */
    DATA_ERROR_NONE(2000, "%s数据不存在"),
    ;

    /**
     * 错误编码
     */
    private final Integer code;

    /**
     * 消息
     */
    private final String msg;

    SystemErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
