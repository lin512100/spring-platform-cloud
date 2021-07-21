package com.platform.common.utils;

import org.springframework.context.ConfigurableApplicationContext;

/**
 * 全局静态Bean
 * @author lin512100
 * @date 2021/7/21
 */
public class BeanUtils {

    /** 将管理上下文的applicationContext设置成静态变量，供全局调用 */
    public static ConfigurableApplicationContext applicationContext;
    /** 定义一个获取已经实例化bean的方法 */
    public static <T> T getBean(Class<T> c){
        return applicationContext.getBean(c);
    }
}
