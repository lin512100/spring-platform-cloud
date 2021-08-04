package com.platform.openfeign.service;

import com.platform.common.consts.SecurityConst;
import com.platform.model.vo.basic.SysBlackRouteVo;
import com.platform.model.vo.basic.SysDictAllVo;
import com.platform.model.vo.basic.SysWhiteRouteVo;
import com.platform.openfeign.consts.ServiceConst;
import com.platform.openfeign.service.impl.BasicApiServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

import static com.platform.openfeign.consts.BasicServiceApiUrl.*;

/**
 * 底层服务Api接口
 * @author lin512100
 * @date 2021/7/22
 */
@Lazy
@FeignClient(value = ServiceConst.PLATFORM_BASIC, fallback = BasicApiServiceImpl.class)
public interface BasicApiService {

    /**
     * 获取所有字典信息
     * @param authorization  授权信息
     * @return List
     */
    @GetMapping(PRE_BASIC_SERVICE + GET_ALL_DICT)
    List<SysDictAllVo> getAllDict(@RequestHeader(SecurityConst.AUTHORIZATION) String authorization);

    /**
     * 获取黑名单信息
     * @param authorization  授权信息
     * @return List
     * */
    @GetMapping(PRE_BASIC_SERVICE + GET_ALL_BLACK_ROUTE)
    List<SysBlackRouteVo> getAllBlackRoute(@RequestHeader(SecurityConst.AUTHORIZATION) String authorization);

    /**
     * 获取白名单信息
     * @param authorization  授权信息
     * @return List
     * */
    @GetMapping(PRE_BASIC_SERVICE + GET_ALL_WHITE_ROUTE)
    List<SysWhiteRouteVo> getAllWhiteRoute(@RequestHeader(SecurityConst.AUTHORIZATION) String authorization);
}
