package com.platform.model.entity.user;

import com.platform.common.annotation.DictValidation;
import com.platform.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

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
    @DictValidation(code = "credential_type")
    private Integer credentialType;

    /**
     * 证件过期时间
     */
    private LocalDateTime credentialExpires;

    /**
     * 用户状态
     * */
    @DictValidation(code = "user_status")
    private Integer userStatus;

    public static final String USER_NAME = "user_name";

    public static final String CREDENTIAL_NUMBER = "credential_number";

    public static final String CREDENTIAL_TYPE = "credential_type";

    public static final String CREDENTIAL_EXPIRES = "credential_expires";

    public static final String USER_STATUS = "user_status";

}
