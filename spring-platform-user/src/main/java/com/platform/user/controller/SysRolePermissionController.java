package com.platform.user.controller;

import javax.annotation.Resource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.platform.common.response.ResultData;
import com.platform.web.utils.PageVo;
import com.platform.model.dto.user.SysRolePermissionDto;
import com.platform.model.vo.user.SysRolePermissionVo;
import com.platform.user.service.SysRolePermissionService;

/**
 * 角色权限中间表 前端控制器
 * @author lin512100
 * @since 2021-07-22
 */
@RestController
@Api(tags = "角色权限中间表 前端控制器")
@RequestMapping("/role_permission")
public class SysRolePermissionController {

    @Resource
    private SysRolePermissionService service;

    @PostMapping("/add")
    @ApiOperation(value = "角色权限中间表新增")
    public ResultData<Long> add(@RequestBody SysRolePermissionDto dto) {
        return ResultData.success(service.add(dto));
    }

    @PostMapping("/del")
    @ApiOperation(value = "角色权限中间表删除")
    public ResultData<Void> del(@RequestBody SysRolePermissionDto dto) {
        service.del(dto);
        return ResultData.success();
    }

    @PostMapping("/modify")
    @ApiOperation(value = "角色权限中间表修改")
    public ResultData<Void> modify(@RequestBody SysRolePermissionDto dto) {
        service.modify(dto);
        return ResultData.success();
    }

    @PostMapping("/list")
    @ApiOperation(value = "角色权限中间表列表")
    public ResultData<PageVo<SysRolePermissionVo>> list(@RequestBody SysRolePermissionDto dto) {
        return ResultData.success(service.list(dto));
    }

}
