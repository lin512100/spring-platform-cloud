package com.platform.basic.file.controller;

import com.platform.basic.file.service.SysFileService;
import com.platform.common.response.ResultData;
import com.platform.basic.file.model.dto.FileUploadDto;
import com.platform.model.dto.basic.SysFileDto;
import com.platform.model.vo.basic.SysFileVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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
    public ResultData<List<SysFileVo>> uploadFile(@ModelAttribute FileUploadDto dto) {
        return ResultData.success(sysFileService.uploadFile(dto));
    }

    @ApiOperation(value = "文件信息上传")
    @GetMapping("/download")
    public void download(@RequestParam("fileUrl") String fileUrl, HttpServletResponse res) throws IOException {
        sysFileService.download(fileUrl, res);
    }

    @PostMapping("/del")
    @ApiOperation(value = "文件信息删除")
    public ResultData<Void> del(@RequestBody SysFileDto dto) {
        sysFileService.del(dto);
        return ResultData.success();
    }

}
