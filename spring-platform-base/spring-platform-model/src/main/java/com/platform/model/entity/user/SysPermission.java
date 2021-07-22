package com.platform.model.entity.user;

import com.platform.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 权限信息
 * @author lin512100
 * @since 2021-07-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysPermission extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 父ID
     */
    private Integer pid;

    /**
     * 资源类型（1：菜单，2：按钮，3：操作）
     */
    private Integer type;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源标识（或者叫权限字符串）
     */
    private String code;

    /**
     * 资源URI
     */
    private String uri;

    /**
     * 序号
     */
    private Integer sort;


    public static final String PID = "pid";

    public static final String TYPE = "type";

    public static final String NAME = "name";

    public static final String CODE = "code";

    public static final String URI = "uri";

    public static final String SORT = "sort";

}
