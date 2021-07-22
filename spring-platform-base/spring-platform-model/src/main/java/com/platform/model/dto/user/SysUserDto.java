package com.platform.model.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.platform.model.base.BaseQuery;

/**
 * 用户信息 通用DTO实体类
 * @author lin512100
 * @since 2021-07-22
 */
@Data
@ApiModel("用户信息通用实体DTO类")
@EqualsAndHashCode(callSuper = true)
public class SysUserDto extends BaseQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "证件号码")
    private String credentialNumber;

    @ApiModelProperty(value = "证件类型")
    private Integer credentialType;

    @ApiModelProperty(value = "邮箱")
    private String email;
    }