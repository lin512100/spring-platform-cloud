package com.platform.model.entity.user;

import com.platform.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户信息
 * @author lin512100
 * @since 2021-07-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 证件号码
     */
    private String credentialNumber;

    /**
     * 证件类型
     */
    private Integer credentialType;

    /**
     * 邮箱
     */
    private String email;


    public static final String USER_NAME = "user_name";

    public static final String CREDENTIAL_NUMBER = "credential_number";

    public static final String CREDENTIAL_TYPE = "credential_type";

    public static final String EMAIL = "email";

}
