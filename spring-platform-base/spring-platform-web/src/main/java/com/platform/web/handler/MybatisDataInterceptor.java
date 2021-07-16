package com.platform.web.handler;

import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.platform.common.enums.DictEnum;
import com.platform.common.utils.ReflexUtils;
import com.platform.web.annotation.DictValidation;
import com.platform.web.service.DictCacheService;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 插入更新校验类
 * @author lin512100
 * @date 2021/6/24
 */
@Component
public class MybatisDataInterceptor implements InnerInterceptor {

    @Lazy
    @Resource
    private DictCacheService dictCacheService;

    @Override
    @SuppressWarnings("unchecked")
    public void beforeUpdate(Executor executor, MappedStatement ms, Object parameter) {
        MapperMethod.ParamMap<Object> mapEntity = (MapperMethod.ParamMap<Object>) parameter;
        Set<FieldRef> fields = new HashSet<>();
        // 获取所有非静态字段
        for (Map.Entry<String, Object> entry : mapEntity.entrySet()) {
            Field[] allFields = ReflexUtils.getAllFields(entry.getValue().getClass());
            fields.addAll(Arrays.stream(allFields)
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .map(field -> new FieldRef(field, entry.getValue()))
                .collect(Collectors.toList()));
        }
        // 校验字典值
        fields.forEach(this::dictValidation);
    }

    /**
     * 校验字典值
     * @param fieldRef 映射信息
     */
    private void dictValidation(FieldRef fieldRef) {
        Field field = fieldRef.getField();
        Object entity = fieldRef.getEntity();

        if (field.getAnnotation(DictValidation.class) == null) {
            return;
        }
        Object value = ReflexUtils.getFieldValueByFieldName(field.getName(), entity);

        // 字典code
        DictEnum dictEnum = field.getAnnotation(DictValidation.class).code();
        // 忽略空枚举
        if (dictEnum.equals(DictEnum.IGNORE_NONE)) {
            return;
        }
        // 校验枚举值是否存在
        if (!dictCacheService.verifyDict(dictEnum.getCustomCode(), value)) {
            throw new PersistenceException(String.format("%s参数不合法", dictEnum.getCustomDesc()));
        }
    }

    /**
     * 字段与值映射类
     */
    @Getter
    @Setter
    @EqualsAndHashCode
    private static class FieldRef {
        /**
         * 字段反射信息
         */
        private Field field;
        /**
         * 反射实体类
         */
        private Object entity;

        public FieldRef(Field field, Object entity) {
            this.field = field;
            this.entity = entity;
        }
    }
}
