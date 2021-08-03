package com.platform.basic.route.controller;

import javax.annotation.Resource;

import com.platform.basic.route.service.SysBlackRouteService;
import com.platform.model.dto.basic.SysBlackRouteDto;
import com.platform.model.vo.basic.SysBlackRouteVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.platform.common.response.ResultData;
import com.platform.mybatis.utils.PageVo;


/**
 * 黑名单 前端控制器
 * @author lin512100
 * @since 2021-08-03
 */
@RestController
@Api(tags = "黑名单 前端控制器")
@RequestMapping("/sysBlackRoute")
public class SysBlackRouteController {

    @Resource
    private SysBlackRouteService service;

    @PostMapping("/add")
    @ApiOperation(value = "黑名单新增")
    public ResultData<Long> add(@RequestBody SysBlackRouteDto dto) {
        return ResultData.success(service.add(dto));
    }

    @PostMapping("/del")
    @ApiOperation(value = "黑名单删除")
    public ResultData<Void> del(@RequestBody SysBlackRouteDto dto) {
        service.del(dto);
        return ResultData.success();
    }

    @PostMapping("/modify")
    @ApiOperation(value = "黑名单修改")
    public ResultData<Void> modify(@RequestBody SysBlackRouteDto dto) {
        service.modify(dto);
        return ResultData.success();
    }

    @PostMapping("/list")
    @ApiOperation(value = "黑名单列表")
    public ResultData<PageVo<SysBlackRouteVo>> list(@RequestBody SysBlackRouteDto dto) {
        return ResultData.success(service.list(dto));
    }

}
