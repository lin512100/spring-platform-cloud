package com.platform.user.service;

import java.util.List;
import com.platform.model.entity.user.SysRolePermission;
import com.platform.web.service.BaseService;
import com.platform.web.utils.PageVo;
import com.platform.model.dto.user.SysRolePermissionDto;
import com.platform.model.vo.user.SysRolePermissionVo;


/**
 * <p>
 * 角色权限中间表 服务类
 * </p>
 *
 * @author lin512100
 * @since 2021-07-22
 */
public interface SysRolePermissionService extends BaseService<SysRolePermission> {

    /**
     * 角色权限中间表新增
     * @param dto {@link SysRolePermissionDto}
     * @return 新增主键
     */
    Long add(SysRolePermissionDto dto);

    /**
     * 角色权限中间表删除
     * @param dto {@link SysRolePermissionDto}
     */
    void del(SysRolePermissionDto dto);

    /**
     * 角色权限中间表修改
     * @param dto {@link SysRolePermissionDto}
     */
    void modify(SysRolePermissionDto dto);

    /**
     * 角色权限中间表列表
     * @param dto {@link SysRolePermissionDto}
     * @return {@link SysRolePermissionVo}
     */
    PageVo<SysRolePermissionVo> list(SysRolePermissionDto dto);

    /**
    * 封装数据列表
    * @param dataList 数据列表
    * @return 封装的数据列表
    */
    List<SysRolePermissionVo> assembleDataList(List<SysRolePermission> dataList);

    /**
     * DTO转实体类
     * @param dto DTO
     * @return 实体类
     */
    SysRolePermission toEntity(SysRolePermissionDto dto);

    /**
     * 实体类转VO
     * @param entity 实体类
     * @return VO
     */
    SysRolePermissionVo toVo(SysRolePermission entity);
    
}
