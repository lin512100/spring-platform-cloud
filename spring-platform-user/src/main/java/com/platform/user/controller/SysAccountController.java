package com.platform.user.controller;

import javax.annotation.Resource;
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
@RequestMapping("/sysAccount")
public class SysAccountController {

    @Resource
    private SysAccountService service;

    @PostMapping("/add")
    @ApiOperation(value = "账户信息新增")
    public ResultData<Long> add(@RequestBody SysAccountDto dto) {
        return ResultData.success(service.add(dto));
    }

    @PostMapping("/del")
    @ApiOperation(value = "账户信息删除")
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
