package com.platform.model.vo.user;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.platform.model.base.BaseQuery;

/**
 * @author lin512100
 * @since 2021-07-22
 */
@Data
@ApiModel("用户信息通用实体VO类")
public class SysUserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "证件号码")
    private String credentialNumber;

    @ApiModelProperty(value = "证件类型")
    private Integer credentialType;

    @ApiModelProperty(value = "证件类型描述")
    private Integer credentialTypeDesc;

    @ApiModelProperty(value = "证件过期时间")
    private LocalDateTime credentialExpires;

    @ApiModelProperty(value = "用户状态")
    private Integer userStatus;

    @ApiModelProperty(value = "用户状态描述")
    private String userStatusDesc;

}


