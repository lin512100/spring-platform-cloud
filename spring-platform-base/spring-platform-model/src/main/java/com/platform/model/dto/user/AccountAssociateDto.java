package com.platform.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 账户合并
 * @author lin512100
 * @date 7/31/2021
 */
@Data
@ApiModel("账户信息关联通用DTO类")
public class AccountAssociateDto {

    @ApiModelProperty(value = "被关联账号")
    private Long associatedAccountId;

    @ApiModelProperty(value = "关联账号")
    private Long accountId;
}
