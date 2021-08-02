package com.platform.user.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.github.pagehelper.Page;
import com.platform.common.annotation.AutoDictFieldValue;
import com.platform.model.dto.user.SysOperationDto;
import com.platform.model.entity.user.SysOperation;
import com.platform.model.vo.user.SysOperationVo;
import com.platform.user.mapper.SysOperationMapper;
import com.platform.user.service.SysOperationService;
import com.platform.mybatis.service.BaseServiceImpl;
import com.platform.mybatis.utils.PageVo;
import com.platform.common.exception.SystemErrorCode;
import com.platform.common.utils.ValidateUtils;
import com.platform.common.utils.SpringBeanUtils;
import org.springframework.stereotype.Service;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

/**
 * 操作权限信息 服务实现类
 * @author lin512100
 * @since 2021-07-31
 */
@Service
public class SysOperationServiceImpl extends BaseServiceImpl<SysOperationMapper, SysOperation> implements SysOperationService {

    @Override
    public Long add(SysOperationDto dto) {
        SysOperation entity = toEntity(dto);
        ValidateUtils.isTrue(entity.getId() != null, SystemErrorCode.PARAM_ERROR, SysOperation.ID);
        this.getBaseMapper().insert(entity);
        return entity.getId();
    }

    @Override
    public void del(SysOperationDto dto) {
        ValidateUtils.noEmpty(dto.getId(), SysOperation.ID);
        SysOperation one = baseMapper.selectById(dto.getId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "操作权限信息");
        this.baseMapper.deleteById(one.getId());
    }

    @Override
    public void modify(SysOperationDto dto) {
        ValidateUtils.noEmpty(dto.getId(), SysOperation.ID);
        SysOperation entity = toEntity(dto);
        // 判断信息是否存在
        SysOperation one = this.getBaseMapper().selectById(dto.getId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "操作权限信息");
        BeanUtils.copyProperties(entity, one);
        this.getBaseMapper().updateById(one);
    }

    @Override
    public PageVo<SysOperationVo> list(SysOperationDto dto) {
        ValidateUtils.isTrue(dto.getPageNo() == null || dto.getPageSize() == null, "分页参数");
        Page<SysOperation> page = PageMethod.startPage(dto.getPageNo(), dto.getPageSize()).doSelectPage(
        () -> this.queryByParams(toEntity(dto)));
        return new PageVo<>(page.getPageSize(), page.getPageNum(), page.getTotal(), assembleDataList(page.getResult()));
    }

    @Override
    public List<SysOperationVo> assembleDataList(List<SysOperation> dataList) {
    if (CollectionUtils.isEmpty(dataList)) {
        return new ArrayList<>();
    }
        return dataList.stream().map(SpringBeanUtils.getBean(SysOperationService.class)::toVo).collect(Collectors.toList());
    }

    @Override
    public SysOperation toEntity(SysOperationDto dto) {
        SysOperation entity = new SysOperation();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    @AutoDictFieldValue
    public SysOperationVo toVo(SysOperation entity) {
        SysOperationVo vo = new SysOperationVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

}
