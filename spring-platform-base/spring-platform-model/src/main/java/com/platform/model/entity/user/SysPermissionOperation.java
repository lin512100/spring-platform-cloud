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
public class SysPermissionOperation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 权限ID
     */
    private Long permissionId;

    /**
     * 操作ID
     */
    private Long operationId;


    public static final String PERMISSION_ID = "permission_id";

    public static final String OPERATION_ID = "operation_id";

}
