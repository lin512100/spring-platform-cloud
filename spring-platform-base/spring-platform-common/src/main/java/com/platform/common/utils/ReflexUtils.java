package com.platform.common.utils;

import com.platform.common.consts.StringConst;
import com.platform.common.exception.SystemErrorCode;
import com.platform.common.exception.SystemException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 反射工具类
 * @author lin512100
 * @date 2021/6/24
 */
public class ReflexUtils {

    /**
     * 通过反射获取类所有对象字段
     * @param clazz 类
     * @return Field
     */
    public static Field[] getAllFields(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        return fieldList.toArray(fields);
    }

    /**
     * 根据属性名获取属性值
     * @param fieldName 字段名
     * @param object    对象
     * @return Object 属性值
     */
    public static Object getFieldValueByFieldName(String fieldName, Object object) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            //设置对象的访问权限，保证对private的属性的访问
            field.setAccessible(true);
            return field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            throw new SystemException(SystemErrorCode.ILLEGAL_ACCESS_EXCEPTION);
        }
    }

    /**
     * 判断类是否包含某个属性名
     * @param clazz     类
     * @param fieldName 属性名
     * @return true 存在
     */
    public static boolean hasField(Class<?> clazz, String fieldName) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            if (fieldName.equals(f.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 反射设置属性值
     */
    public static Object copyProperties(Object object, Class<?> clazz, String fieldName, Object value) {
        Field[] fs = clazz.getDeclaredFields();
        Arrays.stream(fs).forEach(
            field -> {
                if (!field.getName().equals(fieldName)) {
                    return;
                }
                field.setAccessible(true);
                String type = field.getType().toString();
                try {
                    if (type.endsWith(StringConst.STRING)) {
                        field.set(fieldName, ConvertUtils.convertString(value, ""));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
        return value;
    }


}
