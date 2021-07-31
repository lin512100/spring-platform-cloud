package com.platform.user.service;

import java.util.List;

import com.platform.model.dto.user.SysPermissionOperationDto;
import com.platform.model.entity.user.SysPermissionOperation;
import com.platform.model.vo.user.SysPermissionOperationVo;
import com.platform.web.service.BaseService;
import com.platform.web.utils.PageVo;


/**
 * <p>
 * 权限操作中间表 服务类
 * </p>
 *
 * @author lin512100
 * @since 2021-07-31
 */
public interface SysPermissionOperationService extends BaseService<SysPermissionOperation> {

    /**
     * 权限操作中间表新增
     * @param dto {@link SysPermissionOperationDto}
     * @return 新增主键
     */
    Long add(SysPermissionOperationDto dto);

    /**
     * 权限操作中间表删除
     * @param dto {@link SysPermissionOperationDto}
     */
    void del(SysPermissionOperationDto dto);

    /**
     * 权限操作中间表修改
     * @param dto {@link SysPermissionOperationDto}
     */
    void modify(SysPermissionOperationDto dto);

    /**
     * 权限操作中间表列表
     * @param dto {@link SysPermissionOperationDto}
     * @return {@link SysPermissionOperationVo}
     */
    PageVo<SysPermissionOperationVo> list(SysPermissionOperationDto dto);

    /**
    * 封装数据列表
    * @param dataList 数据列表
    * @return 封装的数据列表
    */
    List<SysPermissionOperationVo> assembleDataList(List<SysPermissionOperation> dataList);

    /**
     * DTO转实体类
     * @param dto DTO
     * @return 实体类
     */
    SysPermissionOperation toEntity(SysPermissionOperationDto dto);

    /**
     * 实体类转VO
     * @param entity 实体类
     * @return VO
     */
    SysPermissionOperationVo toVo(SysPermissionOperation entity);
    
}
