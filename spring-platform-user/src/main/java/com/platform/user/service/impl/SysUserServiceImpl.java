package com.platform.user.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import com.platform.model.entity.user.SysUser;
import com.platform.user.mapper.SysUserMapper;
import com.platform.user.service.SysUserService;
import com.platform.web.service.BaseServiceImpl;
import com.platform.web.utils.PageVo;
import com.platform.model.dto.user.SysUserDto;
import com.platform.model.vo.user.SysUserVo;
import com.platform.common.exception.SystemErrorCode;
import com.platform.common.utils.ValidateUtils;
import com.platform.web.annotation.AutoDictFieldValue;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

/**
 * 用户信息 服务实现类
 * @author lin512100
 * @since 2021-07-22
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public Long add(SysUserDto dto) {
        SysUser entity = toEntity(dto);
        ValidateUtils.isTrue(entity.getId() != null, SystemErrorCode.PARAM_ERROR, SysUser.ID);
        this.getBaseMapper().insert(entity);
        return entity.getId();
    }

    @Override
    public void del(SysUserDto dto) {
        ValidateUtils.noEmpty(dto.getId(), SysUser.ID);
        SysUser one = baseMapper.selectById(dto.getId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "用户信息");
        this.baseMapper.deleteById(one.getId());
    }

    @Override
    public void modify(SysUserDto dto) {
        ValidateUtils.noEmpty(dto.getId(), SysUser.ID);
        SysUser entity = toEntity(dto);
        // 判断信息是否存在
        SysUser one = this.getBaseMapper().selectById(dto.getId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "用户信息");
        BeanUtils.copyProperties(entity, one);
        this.getBaseMapper().updateById(one);
    }

    @Override
    public PageVo<SysUserVo> list(SysUserDto dto) {
        ValidateUtils.isTrue(dto.getPageNo() == null || dto.getPageSize() == null, "分页参数");
        Page<SysUser> page = PageMethod.startPage(dto.getPageNo(), dto.getPageSize()).doSelectPage(
        () -> this.queryByParams(toEntity(dto)));
        return new PageVo<>(page.getPageSize(), page.getPageNum(), page.getTotal(), assembleDataList(page.getResult()));
    }

    @Override
    public List<SysUserVo> assembleDataList(List<SysUser> dataList) {
    if (CollectionUtils.isEmpty(dataList)) {
        return new ArrayList<>();
    }
        return dataList.stream().map(this::toVo).collect(Collectors.toList());
    }

    @Override
    public SysUser toEntity(SysUserDto dto) {
        SysUser entity = new SysUser();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    @AutoDictFieldValue
    public SysUserVo toVo(SysUser entity) {
        SysUserVo vo = new SysUserVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

}
