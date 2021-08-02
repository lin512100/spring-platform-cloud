package com.platform.oauth.controller;

import javax.annotation.Resource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.platform.common.response.ResultData;
import com.platform.mybatis.utils.PageVo;
import com.platform.oauth.pojo.dto.OauthClientDetailsDto;
import com.platform.oauth.pojo.vo.OauthClientDetailsVo;
import com.platform.oauth.service.OauthClientDetailsService;

/**
 * 授权认证信息 前端控制器
 * @author lin512100
 * @since 2021-07-21
 */
@RestController
@Api(tags = "授权认证信息 前端控制器")
@RequestMapping("/oauthClientDetails")
public class OauthClientDetailsController {

    @Resource
    private OauthClientDetailsService service;

    @PostMapping("/add")
    @ApiOperation(value = "授权认证信息新增")
    public ResultData<String> add(@RequestBody OauthClientDetailsDto dto) {
        return ResultData.success(service.add(dto));
    }

    @PostMapping("/del")
    @ApiOperation(value = "授权认证信息删除")
    public ResultData<Void> del(@RequestBody OauthClientDetailsDto dto) {
        service.del(dto);
        return ResultData.success();
    }

    @PostMapping("/modify")
    @ApiOperation(value = "授权认证信息修改")
    public ResultData<Void> modify(@RequestBody OauthClientDetailsDto dto) {
        service.modify(dto);
        return ResultData.success();
    }

    @PostMapping("/list")
    @ApiOperation(value = "授权认证信息列表")
    public ResultData<PageVo<OauthClientDetailsVo>> list(@RequestBody OauthClientDetailsDto dto) {
        return ResultData.success(service.list(dto));
    }

}
