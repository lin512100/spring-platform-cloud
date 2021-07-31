package com.platform.user.controller;

import javax.annotation.Resource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.platform.common.response.ResultData;
import com.platform.web.utils.PageVo;
import com.platform.model.dto.user.SysUserRoleDto;
import com.platform.model.vo.user.SysUserRoleVo;
import com.platform.user.service.SysUserRoleService;

/**
 * 用户角色中间表 前端控制器
 * @author lin512100
 * @since 2021-07-22
 */
@RestController
@Api(tags = "用户角色中间表 前端控制器")
@RequestMapping("/user_role")
public class SysUserRoleController {

    @Resource
    private SysUserRoleService service;

    @PostMapping("/add")
    @ApiOperation(value = "用户角色中间表新增")
    public ResultData<Long> add(@RequestBody SysUserRoleDto dto) {
        return ResultData.success(service.add(dto));
    }

    @PostMapping("/del")
    @ApiOperation(value = "用户角色中间表删除")
    public ResultData<Void> del(@RequestBody SysUserRoleDto dto) {
        service.del(dto);
        return ResultData.success();
    }

    @PostMapping("/modify")
    @ApiOperation(value = "用户角色中间表修改")
    public ResultData<Void> modify(@RequestBody SysUserRoleDto dto) {
        service.modify(dto);
        return ResultData.success();
    }

    @PostMapping("/list")
    @ApiOperation(value = "用户角色中间表列表")
    public ResultData<PageVo<SysUserRoleVo>> list(@RequestBody SysUserRoleDto dto) {
        return ResultData.success(service.list(dto));
    }

}
