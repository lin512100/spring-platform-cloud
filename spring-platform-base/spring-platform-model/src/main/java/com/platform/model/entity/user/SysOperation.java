package com.platform.model.entity.user;

import com.platform.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户角色中间表
 * @author lin512100
 * @since 2021-07-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysOperation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 操作名称
     */
    private String operationName;

    /**
     * 操作功能
     */
    private String operationFunc;


    public static final String OPERATION_NAME = "operation_name";

    public static final String OPERATION_FUNC = "operation_func";

}
