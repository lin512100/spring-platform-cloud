package com.platform.model.vo.basic;

import lombok.Data;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.platform.model.base.BaseQuery;

/**
 * @author lin512100
 * @since 2021-08-03
 */
@Data
@ApiModel("黑名单通用实体VO类")
public class SysBlackRouteVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "链接地址")
    private String blackUrl;

    @ApiModelProperty(value = "黑名单名称")
    private String blackName;

    @ApiModelProperty(value = "黑名单IP")
    private String blackIp;
}


