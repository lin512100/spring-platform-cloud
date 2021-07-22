package com.platform.user.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import com.platform.model.entity.user.SysRolePermission;
import com.platform.user.mapper.SysRolePermissionMapper;
import com.platform.user.service.SysRolePermissionService;
import com.platform.web.service.BaseServiceImpl;
import com.platform.web.utils.PageVo;
import com.platform.model.dto.user.SysRolePermissionDto;
import com.platform.model.vo.user.SysRolePermissionVo;
import com.platform.common.exception.SystemErrorCode;
import com.platform.common.utils.ValidateUtils;
import com.platform.web.annotation.AutoDictFieldValue;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

/**
 * 角色权限中间表 服务实现类
 * @author lin512100
 * @since 2021-07-22
 */
@Service
public class SysRolePermissionServiceImpl extends BaseServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

    @Override
    public Long add(SysRolePermissionDto dto) {
        SysRolePermission entity = toEntity(dto);
        ValidateUtils.isTrue(entity.getId() != null, SystemErrorCode.PARAM_ERROR, SysRolePermission.ID);
        this.getBaseMapper().insert(entity);
        return entity.getId();
    }

    @Override
    public void del(SysRolePermissionDto dto) {
        ValidateUtils.noEmpty(dto.getId(), SysRolePermission.ID);
        SysRolePermission one = baseMapper.selectById(dto.getId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "角色权限中间表");
        this.baseMapper.deleteById(one.getId());
    }

    @Override
    public void modify(SysRolePermissionDto dto) {
        ValidateUtils.noEmpty(dto.getId(), SysRolePermission.ID);
        SysRolePermission entity = toEntity(dto);
        // 判断信息是否存在
        SysRolePermission one = this.getBaseMapper().selectById(dto.getId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "角色权限中间表");
        BeanUtils.copyProperties(entity, one);
        this.getBaseMapper().updateById(one);
    }

    @Override
    public PageVo<SysRolePermissionVo> list(SysRolePermissionDto dto) {
        ValidateUtils.isTrue(dto.getPageNo() == null || dto.getPageSize() == null, "分页参数");
        Page<SysRolePermission> page = PageMethod.startPage(dto.getPageNo(), dto.getPageSize()).doSelectPage(
        () -> this.queryByParams(toEntity(dto)));
        return new PageVo<>(page.getPageSize(), page.getPageNum(), page.getTotal(), assembleDataList(page.getResult()));
    }

    @Override
    public List<SysRolePermissionVo> assembleDataList(List<SysRolePermission> dataList) {
    if (CollectionUtils.isEmpty(dataList)) {
        return new ArrayList<>();
    }
        return dataList.stream().map(this::toVo).collect(Collectors.toList());
    }

    @Override
    public SysRolePermission toEntity(SysRolePermissionDto dto) {
        SysRolePermission entity = new SysRolePermission();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    @AutoDictFieldValue
    public SysRolePermissionVo toVo(SysRolePermission entity) {
        SysRolePermissionVo vo = new SysRolePermissionVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

}
