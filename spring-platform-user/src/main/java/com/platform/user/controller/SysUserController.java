package com.platform.user.controller;

import javax.annotation.Resource;

import com.platform.common.annotation.AutoDictFieldValue;
import com.platform.model.vo.OauthUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.platform.common.response.ResultData;
import com.platform.web.utils.PageVo;
import com.platform.model.dto.user.SysUserDto;
import com.platform.model.vo.user.SysUserVo;
import com.platform.user.service.SysUserService;

import java.util.Collections;

/**
 * 用户信息 前端控制器
 * @author lin512100
 * @since 2021-07-22
 */
@RestController
@Api(tags = "用户信息 前端控制器")
@RequestMapping("/sysUser")
public class SysUserController {

    @Resource
    private SysUserService service;

    @PostMapping("/add")
    @ApiOperation(value = "用户信息新增")
    public ResultData<Long> add(@RequestBody SysUserDto dto) {
        return ResultData.success(service.add(dto));
    }

    @PostMapping("/del")
    @ApiOperation(value = "用户信息删除")
    public ResultData<Void> del(@RequestBody SysUserDto dto) {
        service.del(dto);
        return ResultData.success();
    }

    @PostMapping("/modify")
    @ApiOperation(value = "用户信息修改")
    public ResultData<Void> modify(@RequestBody SysUserDto dto) {
        service.modify(dto);
        return ResultData.success();
    }

    @PostMapping("/list")
    @ApiOperation(value = "用户信息列表")
    public ResultData<PageVo<SysUserVo>> list(@RequestBody SysUserDto dto) {
        return ResultData.success(service.list(dto));
    }



}
