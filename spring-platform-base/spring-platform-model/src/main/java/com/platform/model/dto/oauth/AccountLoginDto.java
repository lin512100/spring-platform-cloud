package com.platform.model.dto.oauth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登录实体类
 * @author lin512100
 * @date 2021/8/4
 */
@Data
@ApiModel("登录实体类")
public class AccountLoginDto {

    @ApiModelProperty(value = "账户名")
    private String accName;

    @ApiModelProperty(value = "账户状态")
    private Integer accStatus;

    @ApiModelProperty(value = "账户密码")
    private String accPwd;

    @ApiModelProperty(value = "渠道类型")
    private Integer channel;

    @ApiModelProperty(value = "客户端ID")
    private String clientId;

    @ApiModelProperty(value = "客户端秘钥")
    private String clientSecret;
}
