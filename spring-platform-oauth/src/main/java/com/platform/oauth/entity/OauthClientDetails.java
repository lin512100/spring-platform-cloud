package com.platform.oauth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

import static com.baomidou.mybatisplus.annotation.IdType.INPUT;

/**
 * 授权认证信息
 * @author lin512100
 * @since 2021-07-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OauthClientDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户id
     */
    @TableId(value = "client_id",type = INPUT)
    private String clientId;

    /**
     * 资源id
     */
    private String resourceIds;

    /**
     * 客户秘钥
     */
    private String clientSecret;

    /**
     * 权限范围
     */
    private String scope;

    /**
     * authorization_code,password,refresh_token,implicit,client_credentials
     */
    private String authorizedGrantTypes;

    /**
     * 重定向Uri
     */
    private String webServerRedirectUri;

    /**
     * 权限值
     */
    private String authorities;

    /**
     * 令牌有效时间
     */
    private Integer accessTokenValidity;

    /**
     * 令牌刷新时间
     */
    private Integer refreshTokenValidity;

    /**
     * 预留字段
     */
    private String additionalInformation;

    /**
     * 是否自动授权
     */
    private String autoapprove;


    public static final String CLIENT_ID = "client_id";

    public static final String RESOURCE_IDS = "resource_ids";

    public static final String CLIENT_SECRET = "client_secret";

    public static final String SCOPE = "scope";

    public static final String AUTHORIZED_GRANT_TYPES = "authorized_grant_types";

    public static final String WEB_SERVER_REDIRECT_URI = "web_server_redirect_uri";

    public static final String AUTHORITIES = "authorities";

    public static final String ACCESS_TOKEN_VALIDITY = "access_token_validity";

    public static final String REFRESH_TOKEN_VALIDITY = "refresh_token_validity";

    public static final String ADDITIONAL_INFORMATION = "additional_information";

    public static final String AUTOAPPROVE = "autoapprove";

}
