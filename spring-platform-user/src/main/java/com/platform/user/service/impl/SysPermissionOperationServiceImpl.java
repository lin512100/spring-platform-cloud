package com.platform.user.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import com.platform.common.annotation.AutoDictFieldValue;
import com.platform.common.exception.SystemErrorCode;
import com.platform.common.utils.SpringBeanUtils;
import com.platform.common.utils.ValidateUtils;
import com.platform.model.dto.user.SysPermissionOperationDto;
import com.platform.model.entity.user.SysPermissionOperation;
import com.platform.model.vo.user.SysPermissionOperationVo;
import com.platform.user.mapper.SysPermissionOperationMapper;
import com.platform.user.service.SysPermissionOperationService;
import com.platform.mybatis.service.BaseServiceImpl;
import com.platform.mybatis.utils.PageVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


/**
 * 权限操作中间表 服务实现类
 * @author lin512100
 * @since 2021-07-31
 */
@Service
public class SysPermissionOperationServiceImpl extends BaseServiceImpl<SysPermissionOperationMapper, SysPermissionOperation> implements SysPermissionOperationService {

    @Override
    public Long add(SysPermissionOperationDto dto) {
        SysPermissionOperation entity = toEntity(dto);
        ValidateUtils.isTrue(entity.getId() != null, SystemErrorCode.PARAM_ERROR, SysPermissionOperation.ID);
        this.getBaseMapper().insert(entity);
        return entity.getId();
    }

    @Override
    public void del(SysPermissionOperationDto dto) {
        ValidateUtils.noEmpty(dto.getId(), SysPermissionOperation.ID);
        SysPermissionOperation one = baseMapper.selectById(dto.getId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "权限操作中间表");
        this.baseMapper.deleteById(one.getId());
    }

    @Override
    public void modify(SysPermissionOperationDto dto) {
        ValidateUtils.noEmpty(dto.getId(), SysPermissionOperation.ID);
        SysPermissionOperation entity = toEntity(dto);
        // 判断信息是否存在
        SysPermissionOperation one = this.getBaseMapper().selectById(dto.getId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "权限操作中间表");
        BeanUtils.copyProperties(entity, one);
        this.getBaseMapper().updateById(one);
    }

    @Override
    public PageVo<SysPermissionOperationVo> list(SysPermissionOperationDto dto) {
        ValidateUtils.isTrue(dto.getPageNo() == null || dto.getPageSize() == null, "分页参数");
        Page<SysPermissionOperation> page = PageMethod.startPage(dto.getPageNo(), dto.getPageSize()).doSelectPage(
        () -> this.queryByParams(toEntity(dto)));
        return new PageVo<>(page.getPageSize(), page.getPageNum(), page.getTotal(), assembleDataList(page.getResult()));
    }

    @Override
    public List<SysPermissionOperationVo> assembleDataList(List<SysPermissionOperation> dataList) {
    if (CollectionUtils.isEmpty(dataList)) {
        return new ArrayList<>();
    }
        return dataList.stream().map(SpringBeanUtils.getBean(SysPermissionOperationService.class)::toVo).collect(Collectors.toList());
    }

    @Override
    public SysPermissionOperation toEntity(SysPermissionOperationDto dto) {
        SysPermissionOperation entity = new SysPermissionOperation();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    @AutoDictFieldValue
    public SysPermissionOperationVo toVo(SysPermissionOperation entity) {
        SysPermissionOperationVo vo = new SysPermissionOperationVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

}
