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
@ApiModel("账户信息通用实体VO类")
public class SysAccountVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private Long userId;

    @ApiModelProperty(value = "账户名")
    private String accName;

    @ApiModelProperty(value = "账户密码")
    private String accPwd;

    @ApiModelProperty(value = "渠道类型")
    private Integer channel;
}


