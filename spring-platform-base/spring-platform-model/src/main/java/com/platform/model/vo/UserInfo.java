package com.platform.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * JWT 用户信息
 * @author lin512100
 * @date 2021/8/6
 */
@Getter
@Setter
@ToString
@ApiModel("JWT用户信息")
public class UserInfo {

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "账户名")
    private String accName;

}
