package com.platform.web.service;


import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 默认字典实现类
 * @author lin512100
 * @date 6/26/2021
 */
@Component
@ConditionalOnMissingClass
public class DefaultDictCacheServiceImpl implements DictCacheService{

    @Override
    public boolean verifyDict(String code, Object value) {
        return false;
    }

    @Override
    public String getValueDescFromCodeAndValue(String code, String value) {
        return null;
    }

    @Override
    public List<Void> getDicDataFromCode(String code) {
        return null;
    }
}
