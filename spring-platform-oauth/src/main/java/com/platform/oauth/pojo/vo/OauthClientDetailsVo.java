package com.platform.oauth.pojo.vo;

import lombok.Data;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.platform.model.base.BaseQuery;

/**
 * @author lin512100
 * @since 2021-07-21
 */
@Data
@ApiModel("授权认证信息通用实体VO类")
public class OauthClientDetailsVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "客户id")
    private String clientId;

    @ApiModelProperty(value = "资源id")
    private String resourceIds;

    @ApiModelProperty(value = "客户秘钥")
    private String clientSecret;

    @ApiModelProperty(value = "权限范围")
    private String scope;

    @ApiModelProperty(value = "authorization_code,password,refresh_token,implicit,client_credentials")
    private String authorizedGrantTypes;

    @ApiModelProperty(value = "重定向Uri")
    private String webServerRedirectUri;

    @ApiModelProperty(value = "权限值")
    private String authorities;

    @ApiModelProperty(value = "令牌有效时间")
    private Integer accessTokenValidity;

    @ApiModelProperty(value = "令牌刷新时间")
    private Integer refreshTokenValidity;

    @ApiModelProperty(value = "预留字段")
    private String additionalInformation;

    @ApiModelProperty(value = "是否自动授权")
    private String autoapprove;
}


