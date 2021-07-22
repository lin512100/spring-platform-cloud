package com.platform.model.vo.basic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author lin512100
 * @date 2021/7/22
 */
@Getter
@Setter
@ApiModel("字典信息集合")
public class SysDictAllVo extends SysDictVo {

    @ApiModelProperty(value = "字典项信息")
    List<SysDictItemVo> sysDictItemVoList;
}
