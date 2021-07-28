package com.platform.basic.file.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.platform.basic.file.service.SysFileService;
import com.platform.model.dto.basic.FileUploadDto;
import com.platform.model.dto.basic.SysFileDto;
import com.platform.model.vo.basic.SysFileVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.platform.common.response.ResultData;
import com.platform.web.utils.PageVo;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 文件信息 前端控制器
 * @author lin512100
 * @since 2021-07-27
 */
@Slf4j
@RestController
@RequestMapping("/file")
@Api(tags = "文件信息 前端控制器")
public class SysFileController {

    @Resource
    private SysFileService sysFileService;

    @ApiOperation(value = "文件信息上传")
    @PostMapping("/uploadFile")
    public ResultData<List<SysFileVo>> uploadFile(@ModelAttribute FileUploadDto dto){
        return ResultData.success(sysFileService.uploadFile(dto));
    }

    @PostMapping("/del")
    @ApiOperation(value = "文件信息删除")
    public ResultData<Void> del(@RequestBody SysFileDto dto) {
        sysFileService.del(dto);
        return ResultData.success();
    }

    @PostMapping("/modify")
    @ApiOperation(value = "文件信息修改")
    public ResultData<Void> modify(@RequestBody SysFileDto dto) {
        sysFileService.modify(dto);
        return ResultData.success();
    }

    @PostMapping("/list")
    @ApiOperation(value = "文件信息列表")
    public ResultData<PageVo<SysFileVo>> list(@RequestBody SysFileDto dto) {
        return ResultData.success(sysFileService.list(dto));
    }

}
