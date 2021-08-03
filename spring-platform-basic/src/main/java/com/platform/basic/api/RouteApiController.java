package com.platform.basic.api;

import com.platform.basic.route.service.SysBlackRouteService;
import com.platform.basic.route.service.SysWhiteRouteService;
import com.platform.model.vo.basic.SysBlackRouteVo;
import com.platform.model.vo.basic.SysWhiteRouteVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.platform.openfeign.consts.BasicServiceApiUrl.GET_ALL_BLACK_ROUTE;
import static com.platform.openfeign.consts.BasicServiceApiUrl.GET_ALL_WHITE_ROUTE;

/**
 * 路由接口模块
 * @author lin512100
 * @date 2021/8/3
 */
@RestController
@RequestMapping("/")
@Api(tags = "API接口-路由模块")
public class RouteApiController {

    @Autowired
    private SysBlackRouteService blackRouteService;

    @Autowired
    private SysWhiteRouteService whiteRouteService;

    @GetMapping(GET_ALL_BLACK_ROUTE)
    public List<SysBlackRouteVo> getAllBlackRoute() {
        return blackRouteService.getAllBlackRoute();
    }

    @GetMapping(GET_ALL_WHITE_ROUTE)
    public List<SysWhiteRouteVo> getAllWhiteRoute() {
        return whiteRouteService.getAllWhiteRoute();
    }
}
