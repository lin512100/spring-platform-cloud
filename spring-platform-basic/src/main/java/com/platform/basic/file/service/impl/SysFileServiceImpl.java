package com.platform.basic.file.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.platform.basic.file.mapper.SysFileMapper;
import com.platform.basic.file.service.SysFileService;
import com.platform.basic.file.property.FileProperty;
import com.platform.common.annotation.AutoDictFieldValue;
import com.platform.common.consts.StringConst;
import com.platform.web.utils.CalculateMd5Utils;
import com.platform.web.utils.FileUtils;
import com.platform.basic.file.model.dto.FileUploadDto;
import com.platform.model.dto.basic.SysFileDto;
import com.platform.model.entity.basic.SysFile;
import com.platform.model.vo.basic.SysFileVo;
import com.platform.mybatis.service.BaseServiceImpl;
import com.platform.web.utils.FileWebUtils;
import com.platform.mybatis.utils.PageVo;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件信息 服务实现类
 * @author lin512100
 * @since 2021-07-27
 */
@Slf4j
@Service
public class SysFileServiceImpl extends BaseServiceImpl<SysFileMapper, SysFile> implements SysFileService {

    @Resource
    private FileProperty fileProperty;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<SysFileVo> uploadFile(FileUploadDto dto) {
        List<SysFileVo> fileVos = new ArrayList<>();
        for (MultipartFile file : dto.getFiles()) {
            SysFile sysFile = new SysFile();
            // 文件名
            String fileName = file.getOriginalFilename();
            sysFile.setFileName(fileName);

            // 文件后缀
            assert fileName != null;
            String fileSuffix = fileName.substring(fileName.lastIndexOf(StringConst.PERIOD));
            sysFile.setFileSuffix(fileSuffix);
            // 新文件名
            String uuid = UUID.randomUUID().toString().replace("-", "");

            Long fileSize = file.getSize();
            sysFile.setFileSize(fileSize);

            // 文件MD5值
            sysFile.setFileMd5(CalculateMd5Utils.getFileMd5(file));

            String filePath = FileUtils.transferFile(file, fileProperty.getBasePath(), FileUtils.getDatePath(), uuid);
            sysFile.setFileUrl(filePath);

            // 数据保存
            this.save(sysFile);

            fileVos.add(SpringBeanUtils.getBean(SysFileService.class).toVo(sysFile));
        }
        return fileVos;
    }

    @Override
    public void download(String fileUrl, HttpServletResponse res) throws IOException {
        LambdaQueryWrapper<SysFile> query = new LambdaQueryWrapper<SysFile>();
        query.eq(SysFile::getFileUrl, fileUrl);
        SysFile sysFile = baseMapper.selectOne(query);
        if (sysFile == null) {
            throw new RuntimeException("文件不存在");
        }
        FileWebUtils.download(fileProperty.getBasePath() + sysFile.getFileUrl(), res);
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
        vo.setFileAbsUrl(fileProperty.getFileBaseUrl() + entity.getFileUrl());
        return vo;
    }

}
