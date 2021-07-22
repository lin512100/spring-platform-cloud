package com.platform.model.entity.basic;

import com.platform.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典信息
 * @author lin512100
 * @since 2021-07-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDict extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典编码
     */
    private String dictCode;

    /**
     * 描述
     */
    private String dictDescription;


    public static final String DICT_NAME = "dict_name";

    public static final String DICT_CODE = "dict_code";

    public static final String DICT_DESCRIPTION = "dict_description";

}
