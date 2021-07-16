package com.platform.common.response;

import com.platform.common.exception.SystemErrorCode;
import lombok.Data;

/**
 * @author lin512100
 * @date 2021/5/13
 */
@Data
public class ResultData<T> {

    private Integer code;

    private String msg;

    private T data;

    public final static Integer SUCCESS_CODE = 0;

    public static <T> ResultData<T> success() {
        return new ResultData<>();
    }

    public static <T> ResultData<T> success(T data) {
        return new ResultData<>(data);
    }

    public static <T> ResultData<T> error(Integer code, String msg) {
        return new ResultData<>(code, msg);
    }

    public static <T> ResultData<T> error(Integer code, String msg, T data) {
        return new ResultData<>(code, msg, data);
    }

    public static <T> ResultData<T> error(SystemErrorCode resultErrorCode) {
        return new ResultData<>(resultErrorCode);
    }

    public static <T> ResultData<T> error(SystemErrorCode resultErrorCode, T data) {
        return new ResultData<>(resultErrorCode, data);
    }

    public static <T> ResultData<T> error(SystemErrorCode resultErrorCode, String exMsg) {
        return new ResultData<>(resultErrorCode, exMsg);
    }

    public static <T> ResultData<T> error(SystemErrorCode resultErrorCode, String exMsg, T data) {
        return new ResultData<>(resultErrorCode, exMsg, data);
    }

    private ResultData() {
        this.code = SUCCESS_CODE;
        this.msg = "";
    }

    private ResultData(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ResultData(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private ResultData(T data) {
        this.code = SUCCESS_CODE;
        this.msg = "";
        this.data = data;
    }

    private ResultData(SystemErrorCode resultErrorCode) {
        if (resultErrorCode == null) {
            return;
        }
        this.code = resultErrorCode.getCode();
        this.msg = resultErrorCode.getMsg();
    }

    private ResultData(SystemErrorCode resultErrorCode, String exMsg) {
        if (resultErrorCode == null) {
            return;
        }
        this.code = resultErrorCode.getCode();
        this.msg = String.format(resultErrorCode.getMsg(), exMsg);
    }

    private ResultData(SystemErrorCode resultErrorCode, T data) {
        if (resultErrorCode == null) {
            return;
        }
        this.code = resultErrorCode.getCode();
        this.msg = resultErrorCode.getMsg();
        this.data = data;
    }

    private ResultData(SystemErrorCode resultErrorCode, String exMsg, T data) {
        if (resultErrorCode == null) {
            return;
        }
        this.code = resultErrorCode.getCode();
        this.msg = String.format(resultErrorCode.getMsg(), exMsg);
        this.data = data;
    }

}
