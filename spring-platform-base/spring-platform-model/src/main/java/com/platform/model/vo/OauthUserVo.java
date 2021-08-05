package com.platform.model.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 用户授权
 * @author lin512100
 * @date 2021/6/29
 */
@Data
public class OauthUserVo {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "渠道类型")
    private Integer channel;

    @ApiModelProperty(value = "角色权限")
    private List<String> grantedAuthorityList;
}
