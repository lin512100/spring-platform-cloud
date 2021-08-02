package com.platform.user.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import com.platform.model.entity.user.SysRole;
import com.platform.user.mapper.SysRoleMapper;
import com.platform.user.service.SysRoleService;
import com.platform.mybatis.service.BaseServiceImpl;
import com.platform.mybatis.utils.PageVo;
import com.platform.model.dto.user.SysRoleDto;
import com.platform.model.vo.user.SysRoleVo;
import com.platform.common.exception.SystemErrorCode;
import com.platform.common.utils.ValidateUtils;
import com.platform.common.annotation.AutoDictFieldValue;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

/**
 * 角色信息 服务实现类
 * @author lin512100
 * @since 2021-07-22
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public Long add(SysRoleDto dto) {
        SysRole entity = toEntity(dto);
        ValidateUtils.isTrue(entity.getId() != null, SystemErrorCode.PARAM_ERROR, SysRole.ID);
        this.getBaseMapper().insert(entity);
        return entity.getId();
    }

    @Override
    public void del(SysRoleDto dto) {
        ValidateUtils.noEmpty(dto.getId(), SysRole.ID);
        SysRole one = baseMapper.selectById(dto.getId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "角色信息");
        this.baseMapper.deleteById(one.getId());
    }

    @Override
    public void modify(SysRoleDto dto) {
        ValidateUtils.noEmpty(dto.getId(), SysRole.ID);
        SysRole entity = toEntity(dto);
        // 判断信息是否存在
        SysRole one = this.getBaseMapper().selectById(dto.getId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "角色信息");
        BeanUtils.copyProperties(entity, one);
        this.getBaseMapper().updateById(one);
    }

    @Override
    public PageVo<SysRoleVo> list(SysRoleDto dto) {
        ValidateUtils.isTrue(dto.getPageNo() == null || dto.getPageSize() == null, "分页参数");
        Page<SysRole> page = PageMethod.startPage(dto.getPageNo(), dto.getPageSize()).doSelectPage(
        () -> this.queryByParams(toEntity(dto)));
        return new PageVo<>(page.getPageSize(), page.getPageNum(), page.getTotal(), assembleDataList(page.getResult()));
    }

    @Override
    public List<SysRoleVo> assembleDataList(List<SysRole> dataList) {
    if (CollectionUtils.isEmpty(dataList)) {
        return new ArrayList<>();
    }
        return dataList.stream().map(this::toVo).collect(Collectors.toList());
    }

    @Override
    public SysRole toEntity(SysRoleDto dto) {
        SysRole entity = new SysRole();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    @AutoDictFieldValue
    public SysRoleVo toVo(SysRole entity) {
        SysRoleVo vo = new SysRoleVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

}
