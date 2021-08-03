package com.platform.basic.route.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.platform.basic.route.mapper.SysWhiteRouteMapper;
import com.platform.basic.route.service.SysWhiteRouteService;
import com.platform.common.annotation.AutoDictFieldValue;
import com.platform.model.dto.basic.SysWhiteRouteDto;
import com.platform.model.entity.basic.SysWhiteRoute;
import com.platform.model.vo.basic.SysWhiteRouteVo;
import com.platform.mybatis.service.BaseServiceImpl;
import com.platform.mybatis.utils.PageVo;
import com.platform.common.exception.SystemErrorCode;
import com.platform.common.utils.ValidateUtils;
import com.platform.common.utils.SpringBeanUtils;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

/**
 * 白名单 服务实现类
 * @author lin512100
 * @since 2021-08-03
 */
@Service
public class SysWhiteRouteServiceImpl extends BaseServiceImpl<SysWhiteRouteMapper, SysWhiteRoute> implements SysWhiteRouteService {

    @Override
    public Long add(SysWhiteRouteDto dto) {
        SysWhiteRoute entity = toEntity(dto);
        ValidateUtils.isTrue(entity.getId() != null, SystemErrorCode.PARAM_ERROR, SysWhiteRoute.ID);
        this.getBaseMapper().insert(entity);
        return entity.getId();
    }

    @Override
    public void del(SysWhiteRouteDto dto) {
        ValidateUtils.noEmpty(dto.getId(), SysWhiteRoute.ID);
        SysWhiteRoute one = baseMapper.selectById(dto.getId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "白名单");
        this.baseMapper.deleteById(one.getId());
    }

    @Override
    public void modify(SysWhiteRouteDto dto) {
        ValidateUtils.noEmpty(dto.getId(), SysWhiteRoute.ID);
        SysWhiteRoute entity = toEntity(dto);
        // 判断信息是否存在
        SysWhiteRoute one = this.getBaseMapper().selectById(dto.getId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "白名单");
        BeanUtils.copyProperties(entity, one);
        this.getBaseMapper().updateById(one);
    }

    @Override
    public PageVo<SysWhiteRouteVo> list(SysWhiteRouteDto dto) {
        ValidateUtils.isTrue(dto.getPageNo() == null || dto.getPageSize() == null, "分页参数");
        Page<SysWhiteRoute> page = PageMethod.startPage(dto.getPageNo(), dto.getPageSize()).doSelectPage(
            () -> this.queryByParams(toEntity(dto)));
        return new PageVo<>(page.getPageSize(), page.getPageNum(), page.getTotal(), assembleDataList(page.getResult()));
    }

    @Override
    public List<SysWhiteRouteVo> assembleDataList(List<SysWhiteRoute> dataList) {
        if (CollectionUtils.isEmpty(dataList)) {
            return new ArrayList<>();
        }
        return dataList.stream().map(SpringBeanUtils.getBean(SysWhiteRouteService.class)::toVo).collect(Collectors.toList());
    }

    @Override
    public SysWhiteRoute toEntity(SysWhiteRouteDto dto) {
        SysWhiteRoute entity = new SysWhiteRoute();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    @AutoDictFieldValue
    public SysWhiteRouteVo toVo(SysWhiteRoute entity) {
        SysWhiteRouteVo vo = new SysWhiteRouteVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    @Override
    public List<SysWhiteRouteVo> getAllWhiteRoute() {
        return assembleDataList(list());
    }

}
