package com.platform.web.service;

import java.util.List;

/**
 * 字典缓存服务类
 * @author lin512100
 * @date 2021/6/25
 */
public interface DictCacheService {

    /**
     * 判断code是否存在
     * @param code  字典code
     * @param value 字典值
     * @return 存在为True
     * 字典code
     */
    boolean verifyDict(String code, Object value);

    /**
     * code/value 获取当前系统字典数据描述
     * @param code  字典编码
     * @param value 字典值
     * @return 字典数据描述
     */
    String getValueDescFromCodeAndValue(String code, String value);

    /**
     * 获取code字典数据
     * @param code 字典编码
     */
    List<Void> getDicDataFromCode(String code);
}
