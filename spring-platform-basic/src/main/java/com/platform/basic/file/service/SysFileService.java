package com.platform.basic.file.service;

import java.util.List;

import com.platform.model.dto.basic.FileUploadDto;
import com.platform.model.dto.basic.SysFileDto;
import com.platform.model.entity.basic.SysFile;
import com.platform.model.vo.basic.SysFileVo;
import com.platform.web.service.BaseService;
import com.platform.web.utils.PageVo;


/**
 * <p>
 * 文件信息 服务类
 * </p>
 *
 * @author lin512100
 * @since 2021-07-27
 */
public interface SysFileService extends BaseService<SysFile> {

    /**
     * 文件上传
     * @param dto {@link SysFileDto}
     * @return 新增主键
     */
    Long uploadFile(FileUploadDto dto);

    /**
     * 文件信息删除
     * @param dto {@link SysFileDto}
     */
    void del(SysFileDto dto);

    /**
     * 文件信息修改
     * @param dto {@link SysFileDto}
     */
    void modify(SysFileDto dto);

    /**
     * 文件信息列表
     * @param dto {@link SysFileDto}
     * @return {@link SysFileVo}
     */
    PageVo<SysFileVo> list(SysFileDto dto);

    /**
    * 封装数据列表
    * @param dataList 数据列表
    * @return 封装的数据列表
    */
    List<SysFileVo> assembleDataList(List<SysFile> dataList);

    /**
     * DTO转实体类
     * @param dto DTO
     * @return 实体类
     */
    SysFile toEntity(SysFileDto dto);

    /**
     * 实体类转VO
     * @param entity 实体类
     * @return VO
     */
    SysFileVo toVo(SysFile entity);
    
}
