package com.platform.user.service;

import java.util.List;
import com.platform.model.entity.user.SysUserRole;
import com.platform.web.service.BaseService;
import com.platform.web.utils.PageVo;
import com.platform.model.dto.user.SysUserRoleDto;
import com.platform.model.vo.user.SysUserRoleVo;


/**
 * <p>
 * 用户角色中间表 服务类
 * </p>
 *
 * @author lin512100
 * @since 2021-07-22
 */
public interface SysUserRoleService extends BaseService<SysUserRole> {

    /**
     * 用户角色中间表新增
     * @param dto {@link SysUserRoleDto}
     * @return 新增主键
     */
    Long add(SysUserRoleDto dto);

    /**
     * 用户角色中间表删除
     * @param dto {@link SysUserRoleDto}
     */
    void del(SysUserRoleDto dto);

    /**
     * 用户角色中间表修改
     * @param dto {@link SysUserRoleDto}
     */
    void modify(SysUserRoleDto dto);

    /**
     * 用户角色中间表列表
     * @param dto {@link SysUserRoleDto}
     * @return {@link SysUserRoleVo}
     */
    PageVo<SysUserRoleVo> list(SysUserRoleDto dto);

    /**
    * 封装数据列表
    * @param dataList 数据列表
    * @return 封装的数据列表
    */
    List<SysUserRoleVo> assembleDataList(List<SysUserRole> dataList);

    /**
     * DTO转实体类
     * @param dto DTO
     * @return 实体类
     */
    SysUserRole toEntity(SysUserRoleDto dto);

    /**
     * 实体类转VO
     * @param entity 实体类
     * @return VO
     */
    SysUserRoleVo toVo(SysUserRole entity);
    
}
