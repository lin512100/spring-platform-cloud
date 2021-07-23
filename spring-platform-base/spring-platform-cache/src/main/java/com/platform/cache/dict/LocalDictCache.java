package com.platform.cache.dict;

import com.google.common.collect.Maps;
import com.platform.common.utils.SpringBeanUtils;
import com.platform.model.vo.basic.SysDictAllVo;
import com.platform.model.vo.basic.SysDictVo;
import com.platform.openfeign.service.BasicApiService;
import com.platform.openfeign.utils.FeignUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 内部缓存实现
 * @author lin512100
 * @date 2021/7/22
 */
@Slf4j
public class LocalDictCache implements DictCache{

    private static Map<String, SysDictAllVo> sysDictAll = Maps.newConcurrentMap();

    /**
     * 定时加载数据
     * */
    @Scheduled(cron = "0 0/15 * * * ?")
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
            List<SysDictAllVo> allDict = basicApiService.getAllDict(FeignUtils.getInnerToken());
            if(CollectionUtils.isEmpty(allDict)){
                log.info("LocalDictCache load dict is null!");
                return;
            }
            sysDictAll = allDict.stream().collect(Collectors.toMap(SysDictVo::getDictCode, dict -> dict));
        }
    }

    @Override
    public SysDictAllVo findDictByCode(String dictCode) {
        // 判断数据是否已经加载
        if(sysDictAll.isEmpty()){
            loadData();
        }
        if(sysDictAll.containsKey(dictCode)){
            return sysDictAll.get(dictCode);
        }
        return null;
    }

}
