package com.platform.mybatis.aspect;

import com.platform.cache.dict.DictCache;
import com.platform.common.annotation.DictValidation;
import com.platform.common.exception.SystemErrorCode;
import com.platform.common.exception.SystemException;
import com.platform.common.utils.ReflexUtils;
import com.platform.model.base.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 字典值自动注入
 * @author lin512100
 * @date 2021/6/25
 */
@Aspect
@Slf4j
@Component
public class AutoDictFieldValueAspect {

    @Resource
    private DictCache dictCache;

    /**
     * 字段描述后缀
     */
    private final static String SUFFIX = "Desc";
    /**
     * 字段名
     */
    private final static String TYPE_NAME = "typeName";
    /**
     * 字典名
     */
    private final static String DICT_NAME = "dictName";
    /**
     * 字典值
     */
    private final static String DICT_VALUE = "dictValue";

    /**
     * 切入点
     */
    @Pointcut(value = "@annotation(com.platform.common.annotation.AutoDictFieldValue) && args(entity)")
    private void pointcut(Object entity) {
    }

    /**
     * 返回值切入
     * @param joinPoint 切入点
     * @param entity    参数
     * @param obj       返回值
     * @return obj 修改后的返回值
     */
    @AfterReturning(value = "pointcut(entity)", returning = "obj", argNames = "joinPoint,obj,entity")
    public Object returning(JoinPoint joinPoint, Object obj, Object entity) {
        if (!(entity instanceof BaseEntity)) {
            throw new SystemException(SystemErrorCode.ILLEGAL_ACCESS_EXCEPTION, "此方法只能用于BaseEntity子类");
        }
        // 反射获取所有非静态字段
        List<Field> allFields = Arrays.stream(ReflexUtils.getAllFields(entity.getClass()))
            .filter(field -> !Modifier.isStatic(field.getModifiers())).collect(Collectors.toList());
        // 设置字典值
        List<Map<String, Object>> fieldsMap = new ArrayList<>();

        allFields.stream().filter(field -> field.getAnnotation(DictValidation.class) != null)
            .forEach(field -> {
                Object fieldValue = ReflexUtils.getFieldValueByFieldName(field.getName(), entity);
                if (fieldValue == null) {
                    return;
                }
                Map<String, Object> fieldMap = new HashMap<>();
                fieldMap.put(TYPE_NAME, field.getName());
                fieldMap.put(DICT_NAME, field.getAnnotation(DictValidation.class).code());
                fieldMap.put(DICT_VALUE, fieldValue);
                fieldsMap.add(fieldMap);
            });

        if (CollectionUtils.isEmpty(fieldsMap)) {
            return obj;
        }
        fieldsMap.forEach(
            field -> {
                String typeName = String.valueOf(field.get(TYPE_NAME));
                String dictName = String.valueOf(field.get(DICT_NAME));
                String dictValue = String.valueOf(field.get(DICT_VALUE));
                String desc = dictCache.findDictItemDescByCodeAndValue(dictName, dictValue);
                ReflexUtils.copyProperties(obj, typeName + SUFFIX, desc);
            });

        return obj;
    }

}
