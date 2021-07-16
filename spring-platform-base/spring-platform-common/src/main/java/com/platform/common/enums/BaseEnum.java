package com.platform.common.enums;

/**
 * 基础枚举类
 * @author lin512100
 * @date 2021/5/7
 */
public interface BaseEnum {

    /**
     * 获取code
     * @return Integer
     */
    Object getCustomCode();

    /**
     * 获取描述
     * @return String
     */
    String getCustomDesc();
}
