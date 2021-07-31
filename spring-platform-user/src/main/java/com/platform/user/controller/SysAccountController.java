package com.platform.user.controller;

import javax.annotation.Resource;

import com.platform.model.dto.user.AccountAssociateDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.platform.common.response.ResultData;
import com.platform.web.utils.PageVo;
import com.platform.model.dto.user.SysAccountDto;
import com.platform.model.vo.user.SysAccountVo;
import com.platform.user.service.SysAccountService;

/**
 * 账户信息 前端控制器
 * @author lin512100
 * @since 2021-07-22
 */
@RestController
@Api(tags = "账户信息 前端控制器")
@RequestMapping("/account")
public class SysAccountController {

    @Resource
    private SysAccountService service;

    @PostMapping("/register")
    @ApiOperation(value = "账户信息新增")
    public ResultData<Long> register(@RequestBody SysAccountDto dto) {
        return ResultData.success(service.register(dto));
    }

    @PostMapping("/relevant")
    @ApiOperation(value = "账户信息关联")
    public ResultData<Long> associate(@RequestBody AccountAssociateDto dto) {
        service.associate(dto);
        return ResultData.success();
    }

    @PostMapping("/del")
    @ApiOperation(value = "账户信息注销")
    public ResultData<Void> del(@RequestBody SysAccountDto dto) {
        service.del(dto);
        return ResultData.success();
    }

    @PostMapping("/modify")
    @ApiOperation(value = "账户信息修改")
    public ResultData<Void> modify(@RequestBody SysAccountDto dto) {
        service.modify(dto);
        return ResultData.success();
    }

    @PostMapping("/list")
    @ApiOperation(value = "账户信息列表")
    public ResultData<PageVo<SysAccountVo>> list(@RequestBody SysAccountDto dto) {
        return ResultData.success(service.list(dto));
    }

}
