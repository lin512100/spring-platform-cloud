package com.platform.model.entity.user;

import com.platform.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色权限中间表
 * @author lin512100
 * @since 2021-07-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRolePermission extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 权限ID
     */
    private Long permissionId;


    public static final String ROLE_ID = "role_id";

    public static final String PERMISSION_ID = "permission_id";

}
