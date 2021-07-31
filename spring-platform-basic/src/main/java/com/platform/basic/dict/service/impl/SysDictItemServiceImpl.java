package com.platform.basic.dict.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.platform.basic.dict.service.SysDictService;
import com.platform.model.entity.basic.SysDict;
import com.platform.model.entity.basic.SysDictItem;
import com.platform.basic.dict.mapper.SysDictItemMapper;
import com.platform.basic.dict.service.SysDictItemService;
import com.platform.web.service.BaseServiceImpl;
import com.platform.web.utils.PageVo;
import com.platform.model.dto.basic.SysDictItemDto;
import com.platform.model.vo.basic.SysDictItemVo;
import com.platform.common.exception.SystemErrorCode;
import com.platform.common.utils.ValidateUtils;
import com.platform.common.annotation.AutoDictFieldValue;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

/**
 * 字典项信息 服务实现类
 * @author lin512100
 * @since 2021-07-22
 */
@Service
public class SysDictItemServiceImpl extends BaseServiceImpl<SysDictItemMapper, SysDictItem> implements SysDictItemService {

    @Resource
    private SysDictService dictService;

    @Override
    public Long add(SysDictItemDto dto) {
        SysDictItem entity = toEntity(dto);
        ValidateUtils.isTrue(entity.getId() != null, SystemErrorCode.PARAM_ERROR, SysDictItem.ID);
        this.getBaseMapper().insert(entity);
        return entity.getId();
    }

    @Override
    public void del(SysDictItemDto dto) {
        ValidateUtils.noEmpty(dto.getId(), SysDictItem.ID);
        SysDictItem one = baseMapper.selectById(dto.getId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "字典项信息");
        this.baseMapper.deleteById(one.getId());
    }

    @Override
    public void modify(SysDictItemDto dto) {
        ValidateUtils.noEmpty(dto.getId(), SysDictItem.ID);
        SysDictItem entity = toEntity(dto);
        // 判断信息是否存在
        SysDictItem one = this.getBaseMapper().selectById(dto.getId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "字典项信息");
        BeanUtils.copyProperties(entity, one);
        this.getBaseMapper().updateById(one);
    }

    @Override
    public PageVo<SysDictItemVo> list(SysDictItemDto dto) {
        ValidateUtils.isTrue(dto.getPageNo() == null || dto.getPageSize() == null, "分页参数");
        Page<SysDictItem> page = PageMethod.startPage(dto.getPageNo(), dto.getPageSize()).doSelectPage(
                () -> this.queryByParams(toEntity(dto)));
        return new PageVo<>(page.getPageSize(), page.getPageNum(), page.getTotal(), assembleDataList(page.getResult()));
    }

    @Override
    public List<SysDictItemVo> selectItem(String dictCode) {
        LambdaQueryWrapper<SysDict> dict = new LambdaQueryWrapper<>();
        dict.eq(SysDict::getDictCode, dictCode);
        SysDict sysDict = dictService.getBaseMapper().selectOne(dict);
        ValidateUtils.isTrue(sysDict == null, "字典信息不存在");

        LambdaQueryWrapper<SysDictItem> itemQuery = new LambdaQueryWrapper<>();
        itemQuery.eq(SysDictItem::getDictId, sysDict.getId());
        return assembleDataList(list(itemQuery));
    }

    @Override
    public List<SysDictItemVo> assembleDataList(List<SysDictItem> dataList) {
        if (CollectionUtils.isEmpty(dataList)) {
            return new ArrayList<>();
        }
        return dataList.stream().map(this::toVo).collect(Collectors.toList());
    }

    @Override
    public SysDictItem toEntity(SysDictItemDto dto) {
        SysDictItem entity = new SysDictItem();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    @AutoDictFieldValue
    public SysDictItemVo toVo(SysDictItem entity) {
        SysDictItemVo vo = new SysDictItemVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

}
