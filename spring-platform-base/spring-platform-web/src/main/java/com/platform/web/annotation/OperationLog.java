package com.platform.web.annotation;


import java.lang.annotation.*;


/**
 * 操作日志注解
 * @author lin512100
 * @date 2021/5/7
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {


}
