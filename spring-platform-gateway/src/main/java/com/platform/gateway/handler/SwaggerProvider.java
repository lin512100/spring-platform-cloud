package com.platform.gateway.handler;

import com.platform.common.consts.StringConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.*;

/**
 * Swagger提供配置
 * @author lin512100
 * @date 8/2/2021
 */
@Primary
@Component
public class SwaggerProvider implements SwaggerResourcesProvider {

    private static final String SWAGGER2URL = "/v2/api-docs";
    /**
     * 网关路由
     */
    private final RouteLocator routeLocator;

    /**
     * 网关应用名称
     */
    @Value("${spring.application.name}")
    private String self;

    @Autowired
    public SwaggerProvider(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    /**
     * 对于gateway来说这块比较重要 让swagger能找到对应的服务
     * @return 资源集合
     */
    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<String> routeHosts = new ArrayList<>();
        // 获取所有可用的host：serviceId
        routeLocator.getRoutes().filter(route -> route.getUri().getHost() != null)
                .filter(route -> !self.equals(route.getUri().getHost()))
                .subscribe(route -> routeHosts.add(route.getUri().getHost()));
        // 记录已经添加过的server
        Set<String> addServer = new HashSet<>();
        routeHosts.forEach(instance -> {
            // 拼接url
            String url = StringConst.BIAS + instance.toLowerCase() + SWAGGER2URL;
            if (!addServer.contains(url)) {
                addServer.add(url);
                SwaggerResource swaggerResource = new SwaggerResource();
                swaggerResource.setUrl(url);
                swaggerResource.setName(instance);
                resources.add(swaggerResource);
            }
        });
        return resources;
    }
}
