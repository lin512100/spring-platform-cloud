package com.platform.model.dto.basic;

import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.platform.model.base.BaseQuery;

/**
 * 文件信息 通用DTO实体类
 * @author lin512100
 * @since 2021-07-27
 */
@Data
@ApiModel("文件信息通用实体DTO类")
@EqualsAndHashCode(callSuper = true)
public class SysFileDto extends BaseQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "业务类型")
    private String bizCode;

    @ApiModelProperty(value = "存放地址")
    private String fileUrl;

    @ApiModelProperty(value = "文件名")
    private String fileName;

    @ApiModelProperty(value = "文件格式后缀")
    private String fileSuffix;

    @ApiModelProperty(value = "文件大小")
    private Long fileSize;

    @ApiModelProperty(value = "文件Hash值")
    private String fileMd5;
    }