package com.platform.basic.route.controller;

import javax.annotation.Resource;

import com.platform.basic.route.service.SysWhiteRouteService;
import com.platform.model.dto.basic.SysWhiteRouteDto;
import com.platform.model.vo.basic.SysWhiteRouteVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.platform.common.response.ResultData;
import com.platform.mybatis.utils.PageVo;

/**
 * 白名单 前端控制器
 * @author lin512100
 * @since 2021-08-03
 */
@RestController
@Api(tags = "白名单 前端控制器")
@RequestMapping("/sysWhiteRoute")
public class SysWhiteRouteController {

    @Resource
    private SysWhiteRouteService service;

    @PostMapping("/add")
    @ApiOperation(value = "白名单新增")
    public ResultData<Long> add(@RequestBody SysWhiteRouteDto dto) {
        return ResultData.success(service.add(dto));
    }

    @PostMapping("/del")
    @ApiOperation(value = "白名单删除")
    public ResultData<Void> del(@RequestBody SysWhiteRouteDto dto) {
        service.del(dto);
        return ResultData.success();
    }

    @PostMapping("/modify")
    @ApiOperation(value = "白名单修改")
    public ResultData<Void> modify(@RequestBody SysWhiteRouteDto dto) {
        service.modify(dto);
        return ResultData.success();
    }

    @PostMapping("/list")
    @ApiOperation(value = "白名单列表")
    public ResultData<PageVo<SysWhiteRouteVo>> list(@RequestBody SysWhiteRouteDto dto) {
        return ResultData.success(service.list(dto));
    }

}
