package com.platform.common.utils;

import com.platform.common.exception.SystemErrorCode;
import com.platform.common.exception.SystemException;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 对象互转Map工具类
 * @author lin512100
 * @date 2021/6/23
 */
public class ObjectUtils {

    /**
     * Object转Map
     * @param obj 对象
     * @return Map
     */
    public static Map<String, Object> getObjectToMap(Object obj) {
        try {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            Class<?> clazz = obj.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                // 过滤静态属性
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                String fieldName = field.getName();
                Object value = field.get(obj);
                if (value == null) {
                    value = "";
                }
                map.put(fieldName, value);
            }
            return map;
        } catch (IllegalAccessException e) {
            throw new SystemException(SystemErrorCode.ILLEGAL_ACCESS_EXCEPTION);
        }

    }

    /**
     * Map转Object
     * @param map       map对象
     * @param beanClass 对象
     * @return Object
     */
    public static Object mapToObject(Map<Object, Object> map, Class<?> beanClass){
        try {

            if (map == null) {
                return null;
            }
            Object obj = beanClass.newInstance();
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                if (map.containsKey(field.getName())) {
                    field.set(obj, map.get(field.getName()));
                }
            }
            return obj;
        } catch (IllegalAccessException e) {
            throw new SystemException(SystemErrorCode.ILLEGAL_ACCESS_EXCEPTION);
        } catch (InstantiationException e) {
            throw new SystemException(SystemErrorCode.INSTANTIATION_EXCEPTION);
        }
    }
}
