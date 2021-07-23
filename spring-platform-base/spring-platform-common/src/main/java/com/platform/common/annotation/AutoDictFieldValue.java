package com.platform.common.annotation;

import java.lang.annotation.*;

/**
 * 字典值相互转换关系
 * @author lin512100
 * @date 2021/6/25
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoDictFieldValue {

    /**
     * 属性对应的后缀名
     */
    String suffix() default "Desc";
}
