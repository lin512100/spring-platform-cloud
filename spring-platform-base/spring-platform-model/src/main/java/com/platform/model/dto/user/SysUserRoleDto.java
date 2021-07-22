package com.platform.model.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.platform.model.base.BaseQuery;

/**
 * 用户角色中间表 通用DTO实体类
 * @author lin512100
 * @since 2021-07-22
 */
@Data
@ApiModel("用户角色中间表通用实体DTO类")
@EqualsAndHashCode(callSuper = true)
public class SysUserRoleDto extends BaseQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "角色ID")
    private Integer roleId;
    }