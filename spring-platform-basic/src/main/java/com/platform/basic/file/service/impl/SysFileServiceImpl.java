package com.platform.basic.file.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

import com.platform.basic.file.mapper.SysFileMapper;
import com.platform.basic.file.service.SysFileService;
import com.platform.common.annotation.AutoDictFieldValue;
import com.platform.common.consts.StringConst;
import com.platform.model.dto.basic.FileUploadDto;
import com.platform.model.dto.basic.SysFileDto;
import com.platform.model.entity.basic.SysFile;
import com.platform.model.vo.basic.SysFileVo;
import com.platform.web.service.BaseServiceImpl;
import com.platform.web.utils.PageVo;
import com.platform.common.utils.SpringBeanUtils;
import com.platform.common.exception.SystemErrorCode;
import com.platform.common.utils.ValidateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件信息 服务实现类
 * @author lin512100
 * @since 2021-07-27
 */
@Slf4j
@Service
public class SysFileServiceImpl extends BaseServiceImpl<SysFileMapper, SysFile> implements SysFileService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long uploadFile(FileUploadDto dto) {
        for(MultipartFile file: dto.getFiles()){
            SysFileDto sysFileDto = new SysFileDto();
            // 文件名
            String fileName = file.getOriginalFilename();
            sysFileDto.setFileName(fileName);

            // 文件后缀
            assert fileName != null;
            String fileSuffix = fileName.substring(fileName.lastIndexOf(StringConst.PERIOD));
            sysFileDto.setFileSuffix(fileSuffix);
            // 文件大小

            Long fileSize = file.getSize();
            sysFileDto.setFileSize(fileSize);

            // 文件MD5值



            String uuid = UUID.randomUUID().toString();

            // file.transferTo(dest);
        }
        return null;
    }

    @Override
    public void del(SysFileDto dto) {
        ValidateUtils.noEmpty(dto.getId(), SysFile.ID);
        SysFile one = baseMapper.selectById(dto.getId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "文件信息");
        this.baseMapper.deleteById(one.getId());
    }

    @Override
    public void modify(SysFileDto dto) {
        ValidateUtils.noEmpty(dto.getId(), SysFile.ID);
        SysFile entity = toEntity(dto);
        // 判断信息是否存在
        SysFile one = this.getBaseMapper().selectById(dto.getId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "文件信息");
        BeanUtils.copyProperties(entity, one);
        this.getBaseMapper().updateById(one);
    }

    @Override
    public PageVo<SysFileVo> list(SysFileDto dto) {
        ValidateUtils.isTrue(dto.getPageNo() == null || dto.getPageSize() == null, "分页参数");
        Page<SysFile> page = PageMethod.startPage(dto.getPageNo(), dto.getPageSize()).doSelectPage(
        () -> this.queryByParams(toEntity(dto)));
        return new PageVo<>(page.getPageSize(), page.getPageNum(), page.getTotal(), assembleDataList(page.getResult()));
    }

    @Override
    public List<SysFileVo> assembleDataList(List<SysFile> dataList) {
    if (CollectionUtils.isEmpty(dataList)) {
        return new ArrayList<>();
    }
        return dataList.stream().map(SpringBeanUtils.getBean(SysFileService.class)::toVo).collect(Collectors.toList());
    }

    @Override
    public SysFile toEntity(SysFileDto dto) {
        SysFile entity = new SysFile();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    @AutoDictFieldValue
    public SysFileVo toVo(SysFile entity) {
        SysFileVo vo = new SysFileVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

}
