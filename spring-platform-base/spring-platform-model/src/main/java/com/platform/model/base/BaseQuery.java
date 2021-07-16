package com.platform.model.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;


/**
 * 通用查询基础类
 * @author lin512100
 * @date 2021/6/17
 */
@Data
public class BaseQuery {

    @ApiModelProperty(value = "页码 获取列表必传")
    private Integer pageNo;

    @ApiModelProperty(value = "行数 获取列表必传")
    private Integer pageSize;
}
