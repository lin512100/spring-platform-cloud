package com.platform.user.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import com.platform.model.entity.user.SysUserRole;
import com.platform.user.mapper.SysUserRoleMapper;
import com.platform.user.service.SysUserRoleService;
import com.platform.mybatis.service.BaseServiceImpl;
import com.platform.mybatis.utils.PageVo;
import com.platform.model.dto.user.SysUserRoleDto;
import com.platform.model.vo.user.SysUserRoleVo;
import com.platform.common.exception.SystemErrorCode;
import com.platform.common.utils.ValidateUtils;
import com.platform.common.annotation.AutoDictFieldValue;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

/**
 * 用户角色中间表 服务实现类
 * @author lin512100
 * @since 2021-07-22
 */
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Override
    public Long add(SysUserRoleDto dto) {
        SysUserRole entity = toEntity(dto);
        ValidateUtils.isTrue(entity.getId() != null, SystemErrorCode.PARAM_ERROR, SysUserRole.ID);
        this.getBaseMapper().insert(entity);
        return entity.getId();
    }

    @Override
    public void del(SysUserRoleDto dto) {
        ValidateUtils.noEmpty(dto.getId(), SysUserRole.ID);
        SysUserRole one = baseMapper.selectById(dto.getId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "用户角色中间表");
        this.baseMapper.deleteById(one.getId());
    }

    @Override
    public void modify(SysUserRoleDto dto) {
        ValidateUtils.noEmpty(dto.getId(), SysUserRole.ID);
        SysUserRole entity = toEntity(dto);
        // 判断信息是否存在
        SysUserRole one = this.getBaseMapper().selectById(dto.getId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "用户角色中间表");
        BeanUtils.copyProperties(entity, one);
        this.getBaseMapper().updateById(one);
    }

    @Override
    public PageVo<SysUserRoleVo> list(SysUserRoleDto dto) {
        ValidateUtils.isTrue(dto.getPageNo() == null || dto.getPageSize() == null, "分页参数");
        Page<SysUserRole> page = PageMethod.startPage(dto.getPageNo(), dto.getPageSize()).doSelectPage(
        () -> this.queryByParams(toEntity(dto)));
        return new PageVo<>(page.getPageSize(), page.getPageNum(), page.getTotal(), assembleDataList(page.getResult()));
    }

    @Override
    public List<SysUserRoleVo> assembleDataList(List<SysUserRole> dataList) {
    if (CollectionUtils.isEmpty(dataList)) {
        return new ArrayList<>();
    }
        return dataList.stream().map(this::toVo).collect(Collectors.toList());
    }

    @Override
    public SysUserRole toEntity(SysUserRoleDto dto) {
        SysUserRole entity = new SysUserRole();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    @AutoDictFieldValue
    public SysUserRoleVo toVo(SysUserRole entity) {
        SysUserRoleVo vo = new SysUserRoleVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    @Override
    public List<SysUserRole> getAll(SysUserRoleDto dto) {
        return this.queryByParams(toEntity(dto));
    }

}
