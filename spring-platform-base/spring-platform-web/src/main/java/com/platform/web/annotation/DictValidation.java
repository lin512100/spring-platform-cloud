package com.platform.web.annotation;

import com.platform.common.enums.DictEnum;

import java.lang.annotation.*;

/**
 * 校验字典值合法性
 * @author lin512100
 * @date 2021/6/24
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DictValidation {

    /**
     * 字典code
     */
    DictEnum code() default DictEnum.IGNORE_NONE;

}
