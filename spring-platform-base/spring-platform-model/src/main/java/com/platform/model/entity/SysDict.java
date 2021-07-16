package com.platform.model.entity;

import com.platform.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典信息
 * @author lin512100
 * @since 2021-06-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDict extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 父级主键
     */
    private Long pid;

    /**
     * 字典编码
     */
    private String code;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 字典值
     */
    private String value;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 字典值描述
     */
    private String valueDesc;


    public static final String PID = "pid";

    public static final String CODE = "code";

    public static final String STATE = "state";

    public static final String VALUE = "value";

    public static final String SORT = "sort";

    public static final String VALUE_DESC = "value_desc";

}
