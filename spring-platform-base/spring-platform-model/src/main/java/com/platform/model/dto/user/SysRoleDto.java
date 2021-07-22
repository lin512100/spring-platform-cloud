package com.platform.model.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.platform.model.base.BaseQuery;

/**
 * 角色信息 通用DTO实体类
 * @author lin512100
 * @since 2021-07-22
 */
@Data
@ApiModel("角色信息通用实体DTO类")
@EqualsAndHashCode(callSuper = true)
public class SysRoleDto extends BaseQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    private String roleCode;

    @ApiModelProperty(value = "角色描述")
    private String roleDescription;
    }