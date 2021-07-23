package com.platform.user.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import com.platform.model.entity.user.SysPermission;
import com.platform.user.mapper.SysPermissionMapper;
import com.platform.user.service.SysPermissionService;
import com.platform.web.service.BaseServiceImpl;
import com.platform.web.utils.PageVo;
import com.platform.model.dto.user.SysPermissionDto;
import com.platform.model.vo.user.SysPermissionVo;
import com.platform.common.exception.SystemErrorCode;
import com.platform.common.utils.ValidateUtils;
import com.platform.common.annotation.AutoDictFieldValue;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

/**
 * 权限信息 服务实现类
 * @author lin512100
 * @since 2021-07-22
 */
@Service
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Override
    public Long add(SysPermissionDto dto) {
        SysPermission entity = toEntity(dto);
        ValidateUtils.isTrue(entity.getId() != null, SystemErrorCode.PARAM_ERROR, SysPermission.ID);
        this.getBaseMapper().insert(entity);
        return entity.getId();
    }

    @Override
    public void del(SysPermissionDto dto) {
        ValidateUtils.noEmpty(dto.getId(), SysPermission.ID);
        SysPermission one = baseMapper.selectById(dto.getId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "权限信息");
        this.baseMapper.deleteById(one.getId());
    }

    @Override
    public void modify(SysPermissionDto dto) {
        ValidateUtils.noEmpty(dto.getId(), SysPermission.ID);
        SysPermission entity = toEntity(dto);
        // 判断信息是否存在
        SysPermission one = this.getBaseMapper().selectById(dto.getId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "权限信息");
        BeanUtils.copyProperties(entity, one);
        this.getBaseMapper().updateById(one);
    }

    @Override
    public PageVo<SysPermissionVo> list(SysPermissionDto dto) {
        ValidateUtils.isTrue(dto.getPageNo() == null || dto.getPageSize() == null, "分页参数");
        Page<SysPermission> page = PageMethod.startPage(dto.getPageNo(), dto.getPageSize()).doSelectPage(
        () -> this.queryByParams(toEntity(dto)));
        return new PageVo<>(page.getPageSize(), page.getPageNum(), page.getTotal(), assembleDataList(page.getResult()));
    }

    @Override
    public List<SysPermissionVo> assembleDataList(List<SysPermission> dataList) {
    if (CollectionUtils.isEmpty(dataList)) {
        return new ArrayList<>();
    }
        return dataList.stream().map(this::toVo).collect(Collectors.toList());
    }

    @Override
    public SysPermission toEntity(SysPermissionDto dto) {
        SysPermission entity = new SysPermission();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    @AutoDictFieldValue
    public SysPermissionVo toVo(SysPermission entity) {
        SysPermissionVo vo = new SysPermissionVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

}
