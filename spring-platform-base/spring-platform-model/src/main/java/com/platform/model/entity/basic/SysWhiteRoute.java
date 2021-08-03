package com.platform.model.entity.basic;

import com.platform.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 白名单
 * @author lin512100
 * @since 2021-08-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysWhiteRoute extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 白名单链接
     */
    private String whiteUrl;

    /**
     * 名单名称
     */
    private String whiteName;

    /**
     * 白名单IP
     */
    private String whiteIp;


    public static final String WHITE_URL = "white_url";

    public static final String WHITE_NAME = "white_name";

    public static final String WHITE_IP = "white_ip";

}
