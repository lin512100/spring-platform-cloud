package com.platform.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.web.utils.MysqlDbUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


/**
 * 自定义Service基类
 * @author lin512100
 * @date 2021/5/7
 */
@Slf4j
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {

    @Override
    public List<T> queryByParams(T t) {
        QueryWrapper<T> tQueryWrapper = MysqlDbUtils.queryByParams(t);
        return list(tQueryWrapper);
    }
}
