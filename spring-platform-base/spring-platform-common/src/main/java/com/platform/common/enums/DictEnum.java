package com.platform.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 字典枚举
 * @author lin512100
 * @date 2021/6/16
 */
@Getter
@AllArgsConstructor
public enum DictEnum implements BaseEnum {

    /**
     * 为空枚举
     * */
    IGNORE_NONE(null,"字典空枚举"),

    /**
     * 账户类别
     */
    ACCOUNT_CATEGORY("account_category", "账户类别"),

    /**
     * 性别
     */
    SEX("sex", "性别"),
    ;


    /**
     * 字典code
     */
    @EnumValue
    private final String code;

    /**
     * 字典描述
     */
    private final String desc;

    @Override
    public String getCustomCode() {
        return this.code;
    }

    @Override
    public String getCustomDesc() {
        return this.desc;
    }
}
