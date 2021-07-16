package com.platform.common.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * 异常
 * @author lin512100
 * @date 6/14/2021
 */
public class SystemException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private static final String FORMAT_FLAG = "%s";

    /**
     * 错误码
     */
    private final Integer code;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 详细数据
     */
    private Object data;

    public SystemException() {
        this(SystemErrorCode.UNKNOWN_EXCEPTION);
    }

    public SystemException(ErrorCodeInterface code) {
        code = (code == null ? SystemErrorCode.UNKNOWN_EXCEPTION : code);
        this.code = code.getCode();
        this.msg = code.getMsg();
    }

    public SystemException(ErrorCodeInterface code, Exception e) {
        this(code);
        this.addSuppressed(e);
    }

    public SystemException(ErrorCodeInterface code, String exMsg) {
        this(code);
        if (code.getMsg().contains(FORMAT_FLAG)) {
            this.msg = String.format(code.getMsg(), exMsg);
        } else if (StringUtils.isNotBlank(exMsg)) {
            this.msg = exMsg;
        }
    }

    public SystemException(ErrorCodeInterface code, String exMsg, Object data) {
        this(code);
        if (code.getMsg().contains(FORMAT_FLAG)) {
            this.msg = String.format(code.getMsg(), exMsg);
        } else {
            this.msg = exMsg;
        }
        this.data = data;
    }

    public SystemException(int code, String exMsg) {
        this.code = code;
        this.msg = exMsg;
    }

    public SystemException(int code, String exMsg, Object data) {
        this.code = code;
        this.msg = exMsg;
        this.data = data;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }

    public Integer getCode() {
        return this.code;
    }

    public Object getData() {
        return this.data;
    }

}
