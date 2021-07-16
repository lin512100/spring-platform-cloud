package com.platform.common.utils;

import com.platform.common.exception.ErrorCodeInterface;
import com.platform.common.exception.SystemException;
import com.platform.common.exception.SystemErrorCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * 业务校验工具类
 * @author lin512100
 * @date 2021/6/17
 */
public class ValidateUtils {

    /**
     * 验证字符串不能为空
     * @param chars      校验字符串
     * @param resultCode 错误编码
     * @param fieldName  为空字段名
     * @param <T>        泛型
     */
    public static <T extends CharSequence> void noEmpty(T chars, ErrorCodeInterface resultCode, String fieldName) {
        if (StringUtils.isEmpty(chars)) {
            throw new SystemException(resultCode, fieldName);
        }
    }

    public static <T extends CharSequence> void noEmpty(T chars, String fileName) {
        noEmpty(chars, SystemErrorCode.PARAM_ERROR_NONE, fileName);
    }

    /**
     * 验证对象不能为空
     * @param object     校验对象
     * @param resultCode 错误编码
     * @param fieldName  为空字段名
     * @param <T>        泛型
     */
    public static <T> void noEmpty(T object, ErrorCodeInterface resultCode, String fieldName) {
        if (null == object) {
            throw new SystemException(resultCode, fieldName);
        }
    }

    public static <T> void noEmpty(T object, String fieldName) {
        noEmpty(object, SystemErrorCode.PARAM_ERROR_NONE, fieldName);
    }

    /**
     * 验证集合
     * @param collection  校验集合
     * @param resultCode 错误编码
     * @param collectName 集合名
     * @param <T>         t
     */
    public static <T extends Collection<T>> void noEmpty(T collection, ErrorCodeInterface resultCode, String collectName) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new SystemException(resultCode, collectName);
        }
    }

    public static <T extends Collection<T>> void noEmpty(T collection, String collectName) {
        noEmpty(collection, SystemErrorCode.PARAM_ERROR_NONE, collectName);
    }

    /**
     * 数据判断 True报异常 False 忽略
     * @param check 数据检查
     * @param resultCode 错误编码
     * @param message 消息
     */
    public static void isTrue(boolean check, ErrorCodeInterface resultCode, String message) {
        if (check) {
            throw new SystemException(resultCode, message);
        }
    }

    public static void isTrue(boolean empty, String message) {
        isTrue(empty, SystemErrorCode.PARAM_ERROR_NONE, message);
    }
}
