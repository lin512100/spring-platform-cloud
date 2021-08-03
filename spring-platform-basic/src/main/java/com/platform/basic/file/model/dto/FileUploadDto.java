package com.platform.basic.file.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传实体类
 * @author lin512100
 * @date 2021/7/27
 */
@Data
@AllArgsConstructor
@ApiModel(value = "文件上传实体类")
public class FileUploadDto {

    @ApiModelProperty(value = "请至少上传一个文件")
    private MultipartFile[] files;

}
