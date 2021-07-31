package com.platform.model.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.platform.model.base.BaseQuery;

/**
 * 用户角色中间表 通用DTO实体类
 * @author lin512100
 * @since 2021-07-31
 */
@Data
@ApiModel("用户角色中间表通用实体DTO类")
@EqualsAndHashCode(callSuper = true)
public class SysPermissionOperationDto extends BaseQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "权限ID")
    private Long permissionId;

    @ApiModelProperty(value = "操作ID")
    private Long operationId;
    }