package com.platform.model.dto.basic;

import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.platform.model.base.BaseQuery;

/**
 * 字典信息 通用DTO实体类
 * @author lin512100
 * @since 2021-07-22
 */
@Data
@ApiModel("字典信息通用实体DTO类")
@EqualsAndHashCode(callSuper = true)
public class SysDictDto extends BaseQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "字典名称")
    private String dictName;

    @ApiModelProperty(value = "字典编码")
    private String dictCode;

    @ApiModelProperty(value = "描述")
    private String dictDescription;
    }