package com.platform.basic.route.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import com.platform.basic.route.mapper.SysBlackRouteMapper;
import com.platform.basic.route.service.SysBlackRouteService;
import com.platform.common.annotation.AutoDictFieldValue;
import com.platform.common.exception.SystemErrorCode;
import com.platform.common.utils.SpringBeanUtils;
import com.platform.common.utils.ValidateUtils;
import com.platform.model.dto.basic.SysBlackRouteDto;
import com.platform.model.entity.basic.SysBlackRoute;
import com.platform.model.vo.basic.SysBlackRouteVo;
import com.platform.mybatis.service.BaseServiceImpl;
import com.platform.mybatis.utils.PageVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 黑名单 服务实现类
 * @author lin512100
 * @since 2021-08-03
 */
@Service
public class SysBlackRouteServiceImpl extends BaseServiceImpl<SysBlackRouteMapper, SysBlackRoute> implements SysBlackRouteService {

    @Override
    public Long add(SysBlackRouteDto dto) {
        SysBlackRoute entity = toEntity(dto);
        ValidateUtils.isTrue(entity.getId() != null, SystemErrorCode.PARAM_ERROR, SysBlackRoute.ID);
        this.getBaseMapper().insert(entity);
        return entity.getId();
    }

    @Override
    public void del(SysBlackRouteDto dto) {
        ValidateUtils.noEmpty(dto.getId(), SysBlackRoute.ID);
        SysBlackRoute one = baseMapper.selectById(dto.getId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "黑名单");
        this.baseMapper.deleteById(one.getId());
    }

    @Override
    public void modify(SysBlackRouteDto dto) {
        ValidateUtils.noEmpty(dto.getId(), SysBlackRoute.ID);
        SysBlackRoute entity = toEntity(dto);
        // 判断信息是否存在
        SysBlackRoute one = this.getBaseMapper().selectById(dto.getId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "黑名单");
        BeanUtils.copyProperties(entity, one);
        this.getBaseMapper().updateById(one);
    }

    @Override
    public PageVo<SysBlackRouteVo> list(SysBlackRouteDto dto) {
        ValidateUtils.isTrue(dto.getPageNo() == null || dto.getPageSize() == null, "分页参数");
        Page<SysBlackRoute> page = PageMethod.startPage(dto.getPageNo(), dto.getPageSize()).doSelectPage(
        () -> this.queryByParams(toEntity(dto)));
        return new PageVo<>(page.getPageSize(), page.getPageNum(), page.getTotal(), assembleDataList(page.getResult()));
    }

    @Override
    public List<SysBlackRouteVo> assembleDataList(List<SysBlackRoute> dataList) {
    if (CollectionUtils.isEmpty(dataList)) {
        return new ArrayList<>();
    }
        return dataList.stream().map(SpringBeanUtils.getBean(SysBlackRouteService.class)::toVo).collect(Collectors.toList());
    }

    @Override
    public SysBlackRoute toEntity(SysBlackRouteDto dto) {
        SysBlackRoute entity = new SysBlackRoute();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    @AutoDictFieldValue
    public SysBlackRouteVo toVo(SysBlackRoute entity) {
        SysBlackRouteVo vo = new SysBlackRouteVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    @Override
    public List<SysBlackRouteVo> getAllBlackRoute() {
        return assembleDataList(list());
    }
}
