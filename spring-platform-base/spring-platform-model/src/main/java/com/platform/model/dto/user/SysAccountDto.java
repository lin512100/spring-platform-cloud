package com.platform.model.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.platform.model.base.BaseQuery;

/**
 * 账户信息 通用DTO实体类
 * @author lin512100
 * @since 2021-07-22
 */
@Data
@ApiModel("账户信息通用实体DTO类")
@EqualsAndHashCode(callSuper = true)
public class SysAccountDto extends BaseQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private Long userId;

    @ApiModelProperty(value = "账户名")
    private String accName;

    @ApiModelProperty(value = "账户密码")
    private String accPwd;

    @ApiModelProperty(value = "渠道类型")
    private Integer channel;
    }