package com.platform.user.controller;

import javax.annotation.Resource;

import com.platform.model.dto.user.SysPermissionOperationDto;
import com.platform.model.vo.user.SysPermissionOperationVo;
import com.platform.user.service.SysPermissionOperationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.platform.common.response.ResultData;
import com.platform.mybatis.utils.PageVo;

/**
 * 权限操作中间表 前端控制器
 * @author lin512100
 * @since 2021-07-31
 */
@RestController
@Api(tags = "权限操作中间表 前端控制器")
@RequestMapping("/permission_operation")
public class SysPermissionOperationController {

    @Resource
    private SysPermissionOperationService service;

    @PostMapping("/add")
    @ApiOperation(value = "权限操作中间表新增")
    public ResultData<Long> add(@RequestBody SysPermissionOperationDto dto) {
        return ResultData.success(service.add(dto));
    }

    @PostMapping("/del")
    @ApiOperation(value = "权限操作中间表删除")
    public ResultData<Void> del(@RequestBody SysPermissionOperationDto dto) {
        service.del(dto);
        return ResultData.success();
    }

    @PostMapping("/modify")
    @ApiOperation(value = "权限操作中间表修改")
    public ResultData<Void> modify(@RequestBody SysPermissionOperationDto dto) {
        service.modify(dto);
        return ResultData.success();
    }

    @PostMapping("/list")
    @ApiOperation(value = "权限操作中间表列表")
    public ResultData<PageVo<SysPermissionOperationVo>> list(@RequestBody SysPermissionOperationDto dto) {
        return ResultData.success(service.list(dto));
    }

}
