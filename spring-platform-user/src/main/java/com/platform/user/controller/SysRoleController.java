package com.platform.user.controller;

import javax.annotation.Resource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.platform.common.response.ResultData;
import com.platform.web.utils.PageVo;
import com.platform.model.dto.user.SysRoleDto;
import com.platform.model.vo.user.SysRoleVo;
import com.platform.user.service.SysRoleService;

/**
 * 角色信息 前端控制器
 * @author lin512100
 * @since 2021-07-22
 */
@RestController
@Api(tags = "角色信息 前端控制器")
@RequestMapping("/sysRole")
public class SysRoleController {

    @Resource
    private SysRoleService service;

    @PostMapping("/add")
    @ApiOperation(value = "角色信息新增")
    public ResultData<Long> add(@RequestBody SysRoleDto dto) {
        return ResultData.success(service.add(dto));
    }

    @PostMapping("/del")
    @ApiOperation(value = "角色信息删除")
    public ResultData<Void> del(@RequestBody SysRoleDto dto) {
        service.del(dto);
        return ResultData.success();
    }

    @PostMapping("/modify")
    @ApiOperation(value = "角色信息修改")
    public ResultData<Void> modify(@RequestBody SysRoleDto dto) {
        service.modify(dto);
        return ResultData.success();
    }

    @PostMapping("/list")
    @ApiOperation(value = "角色信息列表")
    public ResultData<PageVo<SysRoleVo>> list(@RequestBody SysRoleDto dto) {
        return ResultData.success(service.list(dto));
    }

}
