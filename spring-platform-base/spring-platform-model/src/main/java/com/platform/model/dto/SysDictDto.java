package com.platform.model.dto;

import com.platform.model.base.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典信息 通用DTO实体类
 * @author lin512100
 * @since 2021-06-25
 */
@Data
@ApiModel("字典信息通用实体DTO类")
@EqualsAndHashCode(callSuper = true)
public class SysDictDto extends BaseQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "父级主键")
    private Long pid;

    @ApiModelProperty(value = "字典编码")
    private String code;

    @ApiModelProperty(value = "状态")
    private Integer state;

    @ApiModelProperty(value = "字典值")
    private String value;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "字典值描述")
    private String valueDesc;
    }