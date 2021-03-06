package com.platform.user.controller;

import javax.annotation.Resource;

import com.platform.model.dto.user.SysOperationDto;
import com.platform.model.vo.user.SysOperationVo;
import com.platform.user.service.SysOperationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.platform.common.response.ResultData;
import com.platform.mybatis.utils.PageVo;

/**
 * 操作权限信息 前端控制器
 * @author lin512100
 * @since 2021-07-31
 */
@RestController
@Api(tags = "操作权限信息 前端控制器")
@RequestMapping("/operation")
public class SysOperationController {

    @Resource
    private SysOperationService service;

    @PostMapping("/add")
    @ApiOperation(value = "操作权限信息新增")
    public ResultData<Long> add(@RequestBody SysOperationDto dto) {
        return ResultData.success(service.add(dto));
    }

    @PostMapping("/del")
    @ApiOperation(value = "操作权限信息删除")
    public ResultData<Void> del(@RequestBody SysOperationDto dto) {
        service.del(dto);
        return ResultData.success();
    }

    @PostMapping("/modify")
    @ApiOperation(value = "操作权限信息修改")
    public ResultData<Void> modify(@RequestBody SysOperationDto dto) {
        service.modify(dto);
        return ResultData.success();
    }

    @PostMapping("/list")
    @ApiOperation(value = "操作权限信息列表")
    public ResultData<PageVo<SysOperationVo>> list(@RequestBody SysOperationDto dto) {
        return ResultData.success(service.list(dto));
    }

}
