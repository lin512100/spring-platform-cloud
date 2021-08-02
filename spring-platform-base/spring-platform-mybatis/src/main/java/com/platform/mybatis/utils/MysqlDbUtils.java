package com.platform.mybatis.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.platform.common.utils.ConvertUtils;
import com.platform.common.utils.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数据库查询帮助类
 * @author lin512100
 * @date 2021/6/23
 */
public class MysqlDbUtils {

    public static <T> QueryWrapper<T> queryByParams(T t) {
        // 所有查询的key
        Map<String, Object> map = ObjectUtils.getObjectToMap(t).entrySet().stream()
            .filter(e -> e.getValue() != null)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        QueryWrapper<T> query = new QueryWrapper<>();
        // 封装查询条件
        if (!map.isEmpty()) {
            map.forEach((key, value) -> {
                if (StringUtils.isEmpty(value)) {
                    return;
                }
                query.eq(ConvertUtils.humpToLine(key), value);
            });
        }
        return query;
    }
}
