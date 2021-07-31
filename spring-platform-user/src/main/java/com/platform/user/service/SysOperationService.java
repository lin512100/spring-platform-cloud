package com.platform.user.service;

import java.util.List;

import com.platform.model.dto.user.SysOperationDto;
import com.platform.model.entity.user.SysOperation;
import com.platform.model.vo.user.SysOperationVo;
import com.platform.web.service.BaseService;
import com.platform.web.utils.PageVo;


/**
 * <p>
 * 操作权限信息 服务类
 * </p>
 *
 * @author lin512100
 * @since 2021-07-31
 */
public interface SysOperationService extends BaseService<SysOperation> {

    /**
     * 操作权限信息新增
     * @param dto {@link SysOperationDto}
     * @return 新增主键
     */
    Long add(SysOperationDto dto);

    /**
     * 操作权限信息删除
     * @param dto {@link SysOperationDto}
     */
    void del(SysOperationDto dto);

    /**
     * 操作权限信息修改
     * @param dto {@link SysOperationDto}
     */
    void modify(SysOperationDto dto);

    /**
     * 操作权限信息列表
     * @param dto {@link SysOperationDto}
     * @return {@link SysOperationVo}
     */
    PageVo<SysOperationVo> list(SysOperationDto dto);

    /**
    * 封装数据列表
    * @param dataList 数据列表
    * @return 封装的数据列表
    */
    List<SysOperationVo> assembleDataList(List<SysOperation> dataList);

    /**
     * DTO转实体类
     * @param dto DTO
     * @return 实体类
     */
    SysOperation toEntity(SysOperationDto dto);

    /**
     * 实体类转VO
     * @param entity 实体类
     * @return VO
     */
    SysOperationVo toVo(SysOperation entity);
    
}
