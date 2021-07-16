package com.platform.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 可用性枚举
 * @author lin512100
 * @date 2021/5/14
 */
@Getter
@AllArgsConstructor
public enum ValidEnum implements BaseEnum {

    /**
     * 无效
     */
    INVALID(0, "无效"),

    /**
     * 有效
     */
    VALID(1, "有效");


    @EnumValue
    private final Integer index;

    private final String desc;

    @Override
    public Integer getCustomCode() {
        return index;
    }

    @Override
    public String getCustomDesc() {
        return desc;
    }

}
