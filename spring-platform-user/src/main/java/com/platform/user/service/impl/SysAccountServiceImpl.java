package com.platform.user.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import com.platform.model.entity.user.SysAccount;
import com.platform.user.mapper.SysAccountMapper;
import com.platform.user.service.SysAccountService;
import com.platform.web.service.BaseServiceImpl;
import com.platform.web.utils.PageVo;
import com.platform.model.dto.user.SysAccountDto;
import com.platform.model.vo.user.SysAccountVo;
import com.platform.common.exception.SystemErrorCode;
import com.platform.common.utils.ValidateUtils;
import com.platform.web.annotation.AutoDictFieldValue;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

/**
 * 账户信息 服务实现类
 * @author lin512100
 * @since 2021-07-22
 */
@Service
public class SysAccountServiceImpl extends BaseServiceImpl<SysAccountMapper, SysAccount> implements SysAccountService {

    @Override
    public Long add(SysAccountDto dto) {
        SysAccount entity = toEntity(dto);
        ValidateUtils.isTrue(entity.getId() != null, SystemErrorCode.PARAM_ERROR, SysAccount.ID);
        this.getBaseMapper().insert(entity);
        return entity.getId();
    }

    @Override
    public void del(SysAccountDto dto) {
        ValidateUtils.noEmpty(dto.getId(), SysAccount.ID);
        SysAccount one = baseMapper.selectById(dto.getId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "账户信息");
        this.baseMapper.deleteById(one.getId());
    }

    @Override
    public void modify(SysAccountDto dto) {
        ValidateUtils.noEmpty(dto.getId(), SysAccount.ID);
        SysAccount entity = toEntity(dto);
        // 判断信息是否存在
        SysAccount one = this.getBaseMapper().selectById(dto.getId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "账户信息");
        BeanUtils.copyProperties(entity, one);
        this.getBaseMapper().updateById(one);
    }

    @Override
    public PageVo<SysAccountVo> list(SysAccountDto dto) {
        ValidateUtils.isTrue(dto.getPageNo() == null || dto.getPageSize() == null, "分页参数");
        Page<SysAccount> page = PageMethod.startPage(dto.getPageNo(), dto.getPageSize()).doSelectPage(
        () -> this.queryByParams(toEntity(dto)));
        return new PageVo<>(page.getPageSize(), page.getPageNum(), page.getTotal(), assembleDataList(page.getResult()));
    }

    @Override
    public List<SysAccountVo> assembleDataList(List<SysAccount> dataList) {
    if (CollectionUtils.isEmpty(dataList)) {
        return new ArrayList<>();
    }
        return dataList.stream().map(this::toVo).collect(Collectors.toList());
    }

    @Override
    public SysAccount toEntity(SysAccountDto dto) {
        SysAccount entity = new SysAccount();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    @AutoDictFieldValue
    public SysAccountVo toVo(SysAccount entity) {
        SysAccountVo vo = new SysAccountVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

}