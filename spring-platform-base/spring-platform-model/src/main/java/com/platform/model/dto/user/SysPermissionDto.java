package com.platform.model.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.platform.model.base.BaseQuery;

/**
 * 权限信息 通用DTO实体类
 * @author lin512100
 * @since 2021-07-22
 */
@Data
@ApiModel("权限信息通用实体DTO类")
@EqualsAndHashCode(callSuper = true)
public class SysPermissionDto extends BaseQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "父ID")
    private Integer pid;

    @ApiModelProperty(value = "资源类型（1：菜单，2：按钮，3：操作）")
    private Integer type;

    @ApiModelProperty(value = "资源名称")
    private String name;

    @ApiModelProperty(value = "资源标识（或者叫权限字符串）")
    private String code;

    @ApiModelProperty(value = "资源URI")
    private String uri;

    @ApiModelProperty(value = "序号")
    private Integer sort;
    }