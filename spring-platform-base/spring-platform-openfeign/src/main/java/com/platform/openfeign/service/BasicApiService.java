package com.platform.openfeign.service;

import com.platform.common.consts.SecurityConst;
import com.platform.model.vo.basic.SysDictAllVo;
import com.platform.openfeign.consts.ServiceConst;
import com.platform.openfeign.service.impl.BasicApiServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

import static com.platform.openfeign.consts.BasicServiceApiUrl.GET_ALL_DICT;

/**
 * 底层服务Api接口
 * @author lin512100
 * @date 2021/7/22
 */
@FeignClient(value = ServiceConst.PLATFORM_BASIC, fallback = BasicApiServiceImpl.class)
public interface BasicApiService {

    /**
     * 获取所有字典信息
     * @param authorization  授权信息
     * @return List
     */
    @GetMapping(GET_ALL_DICT)
    List<SysDictAllVo> getAllDict(@RequestHeader(SecurityConst.AUTHORIZATION) String authorization);
}
