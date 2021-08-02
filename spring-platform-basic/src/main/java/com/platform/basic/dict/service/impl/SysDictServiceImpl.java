package com.platform.basic.dict.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.platform.basic.dict.service.SysDictItemService;
import com.platform.model.entity.basic.SysDict;
import com.platform.basic.dict.mapper.SysDictMapper;
import com.platform.basic.dict.service.SysDictService;
import com.platform.model.vo.basic.SysDictAllVo;
import com.platform.model.vo.basic.SysDictItemVo;
import com.platform.mybatis.service.BaseServiceImpl;
import com.platform.mybatis.utils.PageVo;
import com.platform.model.dto.basic.SysDictDto;
import com.platform.model.vo.basic.SysDictVo;
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
 * 字典信息 服务实现类
 * @author lin512100
 * @since 2021-07-22
 */
@Service
public class SysDictServiceImpl extends BaseServiceImpl<SysDictMapper, SysDict> implements SysDictService {

    @Resource
    private SysDictItemService sysDictItemService;

    @Override
    public Long add(SysDictDto dto) {
        SysDict entity = toEntity(dto);
        ValidateUtils.isTrue(entity.getId() != null, SystemErrorCode.PARAM_ERROR, SysDict.ID);
        this.getBaseMapper().insert(entity);
        return entity.getId();
    }

    @Override
    public void del(SysDictDto dto) {
        ValidateUtils.noEmpty(dto.getId(), SysDict.ID);
        SysDict one = baseMapper.selectById(dto.getId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "字典信息");
        this.baseMapper.deleteById(one.getId());
    }

    @Override
    public void modify(SysDictDto dto) {
        ValidateUtils.noEmpty(dto.getId(), SysDict.ID);
        SysDict entity = toEntity(dto);
        // 判断信息是否存在
        SysDict one = this.getBaseMapper().selectById(dto.getId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "字典信息");
        BeanUtils.copyProperties(entity, one);
        this.getBaseMapper().updateById(one);
    }

    @Override
    public PageVo<SysDictVo> list(SysDictDto dto) {
        ValidateUtils.isTrue(dto.getPageNo() == null || dto.getPageSize() == null, "分页参数");
        Page<SysDict> page = PageMethod.startPage(dto.getPageNo(), dto.getPageSize()).doSelectPage(
            () -> this.queryByParams(toEntity(dto)));
        return new PageVo<>(page.getPageSize(), page.getPageNum(), page.getTotal(), assembleDataList(page.getResult()));
    }

    @Override
    public List<SysDictVo> assembleDataList(List<SysDict> dataList) {
        if (CollectionUtils.isEmpty(dataList)) {
            return new ArrayList<>();
        }
        return dataList.stream().map(this::toVo).collect(Collectors.toList());
    }

    @Override
    public SysDict toEntity(SysDictDto dto) {
        SysDict entity = new SysDict();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    @AutoDictFieldValue
    public SysDictVo toVo(SysDict entity) {
        SysDictVo vo = new SysDictVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    @Override
    public List<SysDictAllVo> getAllDict() {
        // 查询出所有字典
        List<SysDictAllVo> dictList = baseMapper.selectList(new QueryWrapper<>())
            .stream().map(item->{
                SysDictVo sysDictVo = this.toVo(item);
                SysDictAllVo sysDictAllVo = new SysDictAllVo();
                BeanUtils.copyProperties(sysDictVo,sysDictAllVo);
                return sysDictAllVo;
            }).collect(Collectors.toList());

        // 查询出所有字典项信息
        List<SysDictItemVo> dictItems = sysDictItemService.getBaseMapper().selectList(new QueryWrapper<>())
            .stream().map(sysDictItem -> sysDictItemService.toVo(sysDictItem)).collect(Collectors.toList());

        Map<Long, List<SysDictItemVo>> items = dictItems.stream().collect(Collectors.groupingBy(SysDictItemVo::getDictId));
        return dictList.stream().peek(item -> {
                if (items.containsKey(item.getId())) {
                    item.setSysDictItemVoList(items.get(item.getId()));
                } else {
                    item.setSysDictItemVoList(new ArrayList<>());
                }
            }
        ).collect(Collectors.toList());
    }

}
