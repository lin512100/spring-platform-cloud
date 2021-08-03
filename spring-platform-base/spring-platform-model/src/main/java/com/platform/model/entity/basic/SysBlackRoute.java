package com.platform.model.entity.basic;

import com.platform.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 黑名单
 * @author lin512100
 * @since 2021-08-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysBlackRoute extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 链接地址
     */
    private String blackUrl;

    /**
     * 黑名单名称
     */
    private String blackName;

    /**
     * 黑名单IP
     */
    private String blackIp;


    public static final String BLACK_URL = "black_url";

    public static final String BLACK_NAME = "black_name";

    public static final String BLACK_IP = "black_ip";

}
