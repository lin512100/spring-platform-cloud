package com.platform.user.controller;

import javax.annotation.Resource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.platform.common.response.ResultData;
import com.platform.mybatis.utils.PageVo;
import com.platform.model.dto.user.SysPermissionDto;
import com.platform.model.vo.user.SysPermissionVo;
import com.platform.user.service.SysPermissionService;

/**
 * 权限信息 前端控制器
 * @author lin512100
 * @since 2021-07-22
 */
@RestController
@Api(tags = "权限信息 前端控制器")
@RequestMapping("/permission")
public class SysPermissionController {

    @Resource
    private SysPermissionService service;

    @PostMapping("/add")
    @ApiOperation(value = "权限信息新增")
    public ResultData<Long> add(@RequestBody SysPermissionDto dto) {
        return ResultData.success(service.add(dto));
    }

    @PostMapping("/del")
    @ApiOperation(value = "权限信息删除")
    public ResultData<Void> del(@RequestBody SysPermissionDto dto) {
        service.del(dto);
        return ResultData.success();
    }

    @PostMapping("/modify")
    @ApiOperation(value = "权限信息修改")
    public ResultData<Void> modify(@RequestBody SysPermissionDto dto) {
        service.modify(dto);
        return ResultData.success();
    }

    @PostMapping("/list")
    @ApiOperation(value = "权限信息列表")
    public ResultData<PageVo<SysPermissionVo>> list(@RequestBody SysPermissionDto dto) {
        return ResultData.success(service.list(dto));
    }

}
