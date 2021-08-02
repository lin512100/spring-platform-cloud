package com.platform.oauth.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.platform.cache.dict.DictCache;
import com.platform.common.utils.SpringBeanUtils;
import com.platform.model.vo.basic.SysDictAllVo;
import com.platform.oauth.entity.OauthClientDetails;
import com.platform.oauth.mapper.OauthClientDetailsMapper;
import com.platform.oauth.service.OauthClientDetailsService;
import com.platform.mybatis.service.BaseServiceImpl;
import com.platform.mybatis.utils.PageVo;
import com.platform.oauth.pojo.dto.OauthClientDetailsDto;
import com.platform.oauth.pojo.vo.OauthClientDetailsVo;
import com.platform.common.exception.SystemErrorCode;
import com.platform.common.utils.ValidateUtils;
import com.platform.common.annotation.AutoDictFieldValue;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * 授权认证信息 服务实现类
 * @author lin512100
 * @since 2021-07-21
 */
@Service
public class OauthClientDetailsServiceImpl extends BaseServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements OauthClientDetailsService {

    @Override
    public String add(OauthClientDetailsDto dto) {
        OauthClientDetails entity = toEntity(dto);
        ValidateUtils.isTrue(!StringUtils.isEmpty(entity.getClientId()), SystemErrorCode.PARAM_ERROR, OauthClientDetails.CLIENT_ID);
        this.getBaseMapper().insert(entity);
        return entity.getClientId();
    }

    @Override
    public void del(OauthClientDetailsDto dto) {
        ValidateUtils.noEmpty(dto.getClientId(), OauthClientDetails.CLIENT_ID);
        OauthClientDetails one = baseMapper.selectById(dto.getClientId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "授权认证信息");
        this.baseMapper.deleteById(one.getClientId());
    }

    @Override
    public void modify(OauthClientDetailsDto dto) {
        ValidateUtils.noEmpty(dto.getClientId(), OauthClientDetails.CLIENT_ID);
        OauthClientDetails entity = toEntity(dto);
        // 判断信息是否存在
        OauthClientDetails one = this.getBaseMapper().selectById(dto.getClientId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "授权认证信息");
        BeanUtils.copyProperties(entity, one);
        this.getBaseMapper().updateById(one);
    }

    @Override
    public PageVo<OauthClientDetailsVo> list(OauthClientDetailsDto dto) {
        SysDictAllVo sex = SpringBeanUtils.getBean(DictCache.class).findDictByCode("sex");
        System.out.println(sex.toString());
        ValidateUtils.isTrue(dto.getPageNo() == null || dto.getPageSize() == null, "分页参数");
        Page<OauthClientDetails> page = PageMethod.startPage(dto.getPageNo(), dto.getPageSize()).doSelectPage(
        () -> this.queryByParams(toEntity(dto)));
        return new PageVo<>(page.getPageSize(), page.getPageNum(), page.getTotal(), assembleDataList(page.getResult()));
    }

    @Override
    public List<OauthClientDetailsVo> assembleDataList(List<OauthClientDetails> dataList) {
    if (CollectionUtils.isEmpty(dataList)) {
        return new ArrayList<>();
    }
        return dataList.stream().map(this::toVo).collect(Collectors.toList());
    }

    @Override
    public OauthClientDetails toEntity(OauthClientDetailsDto dto) {
        OauthClientDetails entity = new OauthClientDetails();
        BeanUtils.copyProperties(dto, entity);
        if(!StringUtils.isEmpty(dto.getClientSecret())){
            entity.setClientSecret(new BCryptPasswordEncoder().encode(entity.getClientSecret()));
        }
        return entity;
    }

    @Override
    @AutoDictFieldValue
    public OauthClientDetailsVo toVo(OauthClientDetails entity) {
        OauthClientDetailsVo vo = new OauthClientDetailsVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

}
