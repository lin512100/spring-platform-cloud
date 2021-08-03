package com.platform.model.dto.basic;

import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.platform.model.base.BaseQuery;

/**
 * 白名单 通用DTO实体类
 * @author lin512100
 * @since 2021-08-03
 */
@Data
@ApiModel("白名单通用实体DTO类")
@EqualsAndHashCode(callSuper = true)
public class SysWhiteRouteDto extends BaseQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "白名单链接")
    private String whiteUrl;

    @ApiModelProperty(value = "名单名称")
    private String whiteName;

    @ApiModelProperty(value = "白名单IP")
    private String whiteIp;
    }