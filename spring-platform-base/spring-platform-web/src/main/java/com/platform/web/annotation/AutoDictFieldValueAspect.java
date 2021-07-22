package com.platform.web.annotation;

import com.platform.common.enums.DictEnum;
import com.platform.common.exception.SystemErrorCode;
import com.platform.common.exception.SystemException;
import com.platform.common.utils.ReflexUtils;
import com.platform.model.base.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private DictCacheService dictCacheService;

    /**
     * 切入点
     */
    @Pointcut(value = "@annotation(com.platform.web.annotation.AutoDictFieldValue) && args(entity)")
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
    public Object returning(JoinPoint joinPoint, Object obj, Object entity) throws NoSuchMethodException {
        if (entity.getClass().getAnnotation(DictValidation.class) == null) {
            return obj;
        }

        if (!(entity instanceof BaseEntity)) {
            throw new SystemException(SystemErrorCode.ILLEGAL_ACCESS_EXCEPTION, "此方法只能用于BaseEntity拓展类");
        }
        // 反射获取所有非静态字段
        List<Field> allFields = Arrays.stream(ReflexUtils.getAllFields(entity.getClass()))
            .filter(field -> !Modifier.isStatic(field.getModifiers())).collect(Collectors.toList());
        // 设置字典值
        Map<String, Object> fieldsMap = new HashMap<>(8);

        allFields.forEach(field -> {
            Object fieldValue = ReflexUtils.getFieldValueByFieldName(field.getName(), entity);
            if (fieldValue == null) {
                return;
            }
            fieldsMap.put(field.getName(), fieldValue);
        });

        if (fieldsMap.isEmpty()) {
            return obj;
        }

        Map<String, String> dictMap = new HashMap<>(2);
        // 获取方法上的注解
        Class<?> clazz = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        try {
            Method method = clazz.getMethod(methodName, clazz);
            // 自动赋值注解
            AutoDictFieldValue annotation = method.getAnnotation(AutoDictFieldValue.class);
            // 字典校验注解
            DictEnum dictEnum = entity.getClass().getAnnotation(DictValidation.class).code();
            // 获取字典值
            fieldsMap.keySet().forEach(code -> {
                dictMap.put(code + annotation.suffix(), dictCacheService.getValueDescFromCodeAndValue(dictEnum.getCode(), String.valueOf(fieldsMap.get(code))));
            });
            BeanUtils.copyProperties(dictMap, obj);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return obj;
    }

}
