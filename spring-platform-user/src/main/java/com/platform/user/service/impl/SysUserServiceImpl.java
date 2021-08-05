package com.platform.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import com.platform.common.annotation.AutoDictFieldValue;
import com.platform.common.exception.SystemErrorCode;
import com.platform.common.utils.SpringBeanUtils;
import com.platform.common.utils.ValidateUtils;
import com.platform.model.dto.user.SysUserDto;
import com.platform.model.entity.user.SysUser;
import com.platform.model.vo.user.SysUserVo;
import com.platform.mybatis.service.BaseServiceImpl;
import com.platform.mybatis.utils.PageVo;
import com.platform.user.mapper.SysUserMapper;
import com.platform.user.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户信息 服务实现类
 * @author lin512100
 * @since 2021-07-22
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public Long authentication(SysUserDto dto) {
        SysUser entity = toEntity(dto);
        ValidateUtils.noEmpty(entity.getId(), SysUser.ID);
        ValidateUtils.noEmpty(StringUtils.isEmpty(entity.getUserName()), SysUser.USER_NAME);
        ValidateUtils.noEmpty(entity.getCredentialType(), SysUser.CREDENTIAL_TYPE);
        ValidateUtils.noEmpty(entity.getCredentialNumber(), SysUser.CREDENTIAL_NUMBER);
        // 校验之前是否已经实名
        LambdaQueryWrapper<SysUser> query = new LambdaQueryWrapper<>();
        query.eq(SysUser::getCredentialType, dto.getCredentialType());
        query.eq(SysUser::getCredentialNumber, dto.getCredentialNumber());
        SysUser sysUser = this.getBaseMapper().selectOne(query);
        if (sysUser == null) {
            this.getBaseMapper().insert(entity);
            return entity.getId();
        }
        // 只更新证件日期
        sysUser.setCredentialExpires(dto.getCredentialExpires());
        baseMapper.updateById(sysUser);
        return sysUser.getId();
    }

    @Override
    public void modifyStatus(SysUserDto dto) {
        ValidateUtils.noEmpty(dto.getId(), SysUser.ID);
        SysUser one = baseMapper.selectById(dto.getId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "用户信息");
        one.setUserStatus(dto.getUserStatus());
        this.baseMapper.updateById(one);
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
        return dataList.stream().map(SpringBeanUtils.getBean(SysUserService.class)::toVo).collect(Collectors.toList());
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
