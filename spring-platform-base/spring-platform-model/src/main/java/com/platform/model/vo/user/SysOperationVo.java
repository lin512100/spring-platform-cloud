package com.platform.model.vo.user;

import lombok.Data;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.platform.model.base.BaseQuery;

/**
 * @author lin512100
 * @since 2021-07-31
 */
@Data
@ApiModel("用户角色中间表通用实体VO类")
public class SysOperationVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "操作名称")
    private String operationName;

    @ApiModelProperty(value = "操作功能")
    private String operationFunc;
}


