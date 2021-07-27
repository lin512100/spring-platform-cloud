package com.platform.basic.dict.controller;

import com.platform.basic.dict.service.SysDictService;
import com.platform.common.response.ResultData;
import com.platform.model.dto.basic.SysDictDto;
import com.platform.model.vo.basic.SysDictVo;
import com.platform.web.utils.PageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 字典信息 前端控制器
 * @author lin512100
 * @since 2021-07-22
 */
@RestController
@Api(tags = "字典信息 前端控制器")
@RequestMapping("/sysDict")
public class SysDictController {

    @Resource
    private SysDictService service;

    @PostMapping("/add")
    @ApiOperation(value = "字典信息新增")
    public ResultData<Long> add(@RequestBody SysDictDto dto) {
        return ResultData.success(service.add(dto));
    }

    @PostMapping("/del")
    @ApiOperation(value = "字典信息删除")
    public ResultData<Void> del(@RequestBody SysDictDto dto) {
        service.del(dto);
        return ResultData.success();
    }

    @PostMapping("/modify")
    @ApiOperation(value = "字典信息修改")
    public ResultData<Void> modify(@RequestBody SysDictDto dto) {
        service.modify(dto);
        return ResultData.success();
    }

    @PostMapping("/list")
    @ApiOperation(value = "字典信息列表")
    public ResultData<PageVo<SysDictVo>> list(@RequestBody SysDictDto dto) {
        return ResultData.success(service.list(dto));
    }

}
