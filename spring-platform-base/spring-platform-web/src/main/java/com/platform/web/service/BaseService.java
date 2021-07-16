package com.platform.web.service;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 基础服务
 * @author lin512100
 * @date 6/14/2021
 */
public interface BaseService<T> extends IService<T> {

    /**
     * 插入一条数据 返回主键
     * @param t 数据查询实体类
     * @return List<T> 数据返回
     */
    List<T> queryByParams(T t);

}
