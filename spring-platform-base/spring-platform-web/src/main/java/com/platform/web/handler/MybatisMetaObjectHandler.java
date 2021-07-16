package com.platform.web.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.platform.common.enums.ValidEnum;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自定义插入更新元数据控制器
 * @author lin512100
 * @date 2021/6/24
 */
@Component
public class MybatisMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        // 创建时间为系统时间
        this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        // 更新时间为系统时间
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        // 默认有效值
        this.setFieldValByName("valid", ValidEnum.VALID.getCustomCode(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }
}
