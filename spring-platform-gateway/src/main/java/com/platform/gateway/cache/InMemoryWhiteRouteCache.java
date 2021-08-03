package com.platform.gateway.cache;

import com.platform.common.utils.SpringBeanUtils;
import com.platform.model.vo.basic.SysWhiteRouteVo;
import com.platform.openfeign.service.BasicApiService;
import com.platform.openfeign.utils.FeignUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 内存加载白名单
 * @author lin512100
 * @date 2021/8/3
 */
@Slf4j
public class InMemoryWhiteRouteCache implements WhiteRouteCache {

    private static Map<String, SysWhiteRouteVo> BLACK_ROUTE = new ConcurrentHashMap<>();

    /**
     * 定时加载数据
     * */
    @Scheduled(cron = "0 0/1 * * * ?")
    // @Scheduled(cron = "* * * * * ? *")
    protected void preload() {
        log.info("EntDicCache..preload..synchronous start");
        // 加载数据到内存
        loadData();
        log.info("EntDicCache..preload..synchronous end");
    }

    private void loadData() {
        synchronized (this) {
            if(SpringBeanUtils.applicationContext == null){
                throw new RuntimeException("未能加载全局上下文（BeanUtils.applicationContext = SpringApplication.run(XXX.class);）");
            }
            BasicApiService basicApiService = SpringBeanUtils.getBean(BasicApiService.class);
            List<SysWhiteRouteVo> whiteRoute = basicApiService.getAllWhiteRoute(FeignUtils.getInnerToken());
            if(CollectionUtils.isEmpty(whiteRoute)){
                log.info("LocalDictCache load dict is null!");
                return;
            }
            BLACK_ROUTE = whiteRoute.stream().collect(Collectors.toMap(SysWhiteRouteVo::getWhiteUrl, dict -> dict));
        }
    }

    @Override
    public List<SysWhiteRouteVo> getAllWhiteRoute() {
        // 判断数据是否已经加载
        if(BLACK_ROUTE.isEmpty()){
            loadData();
        }
        return new ArrayList<>(BLACK_ROUTE.values());
    }
}
