package com.platform.model.entity.user;

import com.platform.common.annotation.DictValidation;
import com.platform.model.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 账户信息
 * @author lin512100
 * @since 2021-07-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysAccount extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private Long userId;

    /**
     * 账户名
     */
    private String accName;

    /**
     * 账户密码
     */
    private String accPwd;

    /**
     * 渠道类型
     */
    @DictValidation(code = "acc_channel")
    private Integer channel;

    /**
     * 账户状态
     */
    @DictValidation(code = "acc_status")
    private Integer accStatus;

    public static final String USER_ID = "user_id";

    public static final String ACC_NAME = "acc_name";

    public static final String ACC_PWD = "acc_pwd";

    public static final String CHANNEL = "channel";

    public static final String ACC_STATUS = "acc_status";

}
