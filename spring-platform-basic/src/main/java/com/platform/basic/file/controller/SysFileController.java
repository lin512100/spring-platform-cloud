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
    private SysFileService service;

    /**
     * 单文件上传
     * @param dto 文件上传类
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadFile")
    public String uploadFile(@ModelAttribute FileUploadDto dto) throws IOException {
return null;

    }

    /**
     * 多文件上传
     * @param request 文件列表
     */
    @PostMapping("/uploadFiles")
    @ResponseBody
    public String uploadFiles(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        String filePath = "E:/temptest/";
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            if (file.isEmpty()) {
                System.out.println("未找到附件。。。");
                System.out.println("上传第" + (++i) + "个文件失败");
                i--; // 需要判断下一个附件是否可以进行上传，把上一行+1的下标减回去
                continue;
            }
            String fileName = file.getOriginalFilename();

            File dest = new File(filePath + fileName);
            try {
                file.transferTo(dest);
                log.info("第" + (i + 1) + "个文件上传成功");
            } catch (IOException e) {
                log.error(e.toString(), e);
                return "上传第" + (++i) + "个文件失败";
            }
        }

        return "上传成功";
    }

    @PostMapping("/del")
    @ApiOperation(value = "文件信息删除")
    public ResultData<Void> del(@RequestBody SysFileDto dto) {
        service.del(dto);
        return ResultData.success();
    }

    @PostMapping("/modify")
    @ApiOperation(value = "文件信息修改")
    public ResultData<Void> modify(@RequestBody SysFileDto dto) {
        service.modify(dto);
        return ResultData.success();
    }

    @PostMapping("/list")
    @ApiOperation(value = "文件信息列表")
    public ResultData<PageVo<SysFileVo>> list(@RequestBody SysFileDto dto) {
        return ResultData.success(service.list(dto));
    }

}
