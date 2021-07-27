package com.platform.basic.dict.controller;

import com.platform.basic.dict.service.SysDictService;
import com.platform.model.vo.basic.SysDictAllVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.platform.openfeign.consts.BasicServiceApiUrl.GET_ALL_DICT;

/**
 *
 * @author lin512100
 * @date 2021/7/22
 */
@RestController
@Api(tags = "API接口-字典模块")
@RequestMapping("/")
public class SysDictApiController {

    @Resource
    private SysDictService sysDictService;

    @GetMapping(GET_ALL_DICT)
    public List<SysDictAllVo> getAllDict(){
        return sysDictService.getAllDict();
    }
}
