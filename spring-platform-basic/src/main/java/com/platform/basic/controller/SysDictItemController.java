package com.platform.basic.controller;

import javax.annotation.Resource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.platform.common.response.ResultData;
import com.platform.web.utils.PageVo;
import com.platform.model.dto.basic.SysDictItemDto;
import com.platform.model.vo.basic.SysDictItemVo;
import com.platform.basic.service.SysDictItemService;

/**
 * 字典项信息 前端控制器
 * @author lin512100
 * @since 2021-07-22
 */
@RestController
@Api(tags = "字典项信息 前端控制器")
@RequestMapping("/sysDictItem")
public class SysDictItemController {

    @Resource
    private SysDictItemService service;

    @PostMapping("/add")
    @ApiOperation(value = "字典项信息新增")
    public ResultData<Long> add(@RequestBody SysDictItemDto dto) {
        return ResultData.success(service.add(dto));
    }

    @PostMapping("/del")
    @ApiOperation(value = "字典项信息删除")
    public ResultData<Void> del(@RequestBody SysDictItemDto dto) {
        service.del(dto);
        return ResultData.success();
    }

    @PostMapping("/modify")
    @ApiOperation(value = "字典项信息修改")
    public ResultData<Void> modify(@RequestBody SysDictItemDto dto) {
        service.modify(dto);
        return ResultData.success();
    }

    @PostMapping("/list")
    @ApiOperation(value = "字典项信息列表")
    public ResultData<PageVo<SysDictItemVo>> list(@RequestBody SysDictItemDto dto) {
        return ResultData.success(service.list(dto));
    }

}
