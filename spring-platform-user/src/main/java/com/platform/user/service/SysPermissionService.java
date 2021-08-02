package com.platform.user.service;

import java.util.List;
import com.platform.model.entity.user.SysPermission;
import com.platform.mybatis.service.BaseService;
import com.platform.mybatis.utils.PageVo;
import com.platform.model.dto.user.SysPermissionDto;
import com.platform.model.vo.user.SysPermissionVo;


/**
 * <p>
 * 权限信息 服务类
 * </p>
 *
 * @author lin512100
 * @since 2021-07-22
 */
public interface SysPermissionService extends BaseService<SysPermission> {

    /**
     * 权限信息新增
     * @param dto {@link SysPermissionDto}
     * @return 新增主键
     */
    Long add(SysPermissionDto dto);

    /**
     * 权限信息删除
     * @param dto {@link SysPermissionDto}
     */
    void del(SysPermissionDto dto);

    /**
     * 权限信息修改
     * @param dto {@link SysPermissionDto}
     */
    void modify(SysPermissionDto dto);

    /**
     * 权限信息列表
     * @param dto {@link SysPermissionDto}
     * @return {@link SysPermissionVo}
     */
    PageVo<SysPermissionVo> list(SysPermissionDto dto);

    /**
    * 封装数据列表
    * @param dataList 数据列表
    * @return 封装的数据列表
    */
    List<SysPermissionVo> assembleDataList(List<SysPermission> dataList);

    /**
     * DTO转实体类
     * @param dto DTO
     * @return 实体类
     */
    SysPermission toEntity(SysPermissionDto dto);

    /**
     * 实体类转VO
     * @param entity 实体类
     * @return VO
     */
    SysPermissionVo toVo(SysPermission entity);
    
}
