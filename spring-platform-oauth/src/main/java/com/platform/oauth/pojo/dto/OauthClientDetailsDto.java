package com.platform.oauth.pojo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.platform.model.base.BaseQuery;

/**
 * 授权认证信息 通用DTO实体类
 * @author lin512100
 * @since 2021-07-21
 */
@Data
@ApiModel("授权认证信息通用实体DTO类")
@EqualsAndHashCode(callSuper = true)
public class OauthClientDetailsDto extends BaseQuery {

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