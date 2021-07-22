package com.platform.model.entity.basic;

import com.platform.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典项信息
 * @author lin512100
 * @since 2021-07-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDictItem extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 字典id
     */
    private Long dictId;

    /**
     * 字典项文本
     */
    private String itemText;

    /**
     * 字典项值
     */
    private String itemValue;

    /**
     * 描述
     */
    private String itemDescription;

    /**
     * 排序
     */
    private Integer sort;


    public static final String DICT_ID = "dict_id";

    public static final String ITEM_TEXT = "item_text";

    public static final String ITEM_VALUE = "item_value";

    public static final String ITEM_DESCRIPTION = "item_description";

    public static final String SORT = "sort";

}
