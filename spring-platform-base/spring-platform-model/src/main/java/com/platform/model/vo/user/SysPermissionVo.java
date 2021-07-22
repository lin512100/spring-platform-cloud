package com.platform.model.vo.user;

import lombok.Data;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.platform.model.base.BaseQuery;

/**
 * @author lin512100
 * @since 2021-07-22
 */
@Data
@ApiModel("权限信息通用实体VO类")
public class SysPermissionVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "父ID")
    private Integer pid;

    @ApiModelProperty(value = "资源类型（1：菜单，2：按钮，3：操作）")
    private Integer type;

    @ApiModelProperty(value = "资源名称")
    private String name;

    @ApiModelProperty(value = "资源标识（或者叫权限字符串）")
    private String code;

    @ApiModelProperty(value = "资源URI")
    private String uri;

    @ApiModelProperty(value = "序号")
    private Integer sort;
}


