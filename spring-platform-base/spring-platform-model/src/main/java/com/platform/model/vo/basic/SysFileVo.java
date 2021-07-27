package com.platform.model.vo.basic;

import lombok.Data;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.platform.model.base.BaseQuery;

/**
 * @author lin512100
 * @since 2021-07-27
 */
@Data
@ApiModel("文件信息通用实体VO类")
public class SysFileVo implements Serializable {

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
    private Double fileSize;

    @ApiModelProperty(value = "文件Hash值")
    private String fileMd5;
}


