package com.platform.model.vo.basic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * @author lin512100
 * @date 2021/7/22
 */
@Data
@ApiModel("字典信息集合")
@EqualsAndHashCode(callSuper = true)
public class SysDictAllVo extends SysDictVo {

    @ApiModelProperty(value = "字典项信息")
    List<SysDictItemVo> sysDictItemVoList;
}
