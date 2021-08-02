package com.platform.user.service;

import java.util.List;
import com.platform.model.entity.user.SysRole;
import com.platform.mybatis.service.BaseService;
import com.platform.mybatis.utils.PageVo;
import com.platform.model.dto.user.SysRoleDto;
import com.platform.model.vo.user.SysRoleVo;


/**
 * <p>
 * 角色信息 服务类
 * </p>
 *
 * @author lin512100
 * @since 2021-07-22
 */
public interface SysRoleService extends BaseService<SysRole> {

    /**
     * 角色信息新增
     * @param dto {@link SysRoleDto}
     * @return 新增主键
     */
    Long add(SysRoleDto dto);

    /**
     * 角色信息删除
     * @param dto {@link SysRoleDto}
     */
    void del(SysRoleDto dto);

    /**
     * 角色信息修改
     * @param dto {@link SysRoleDto}
     */
    void modify(SysRoleDto dto);

    /**
     * 角色信息列表
     * @param dto {@link SysRoleDto}
     * @return {@link SysRoleVo}
     */
    PageVo<SysRoleVo> list(SysRoleDto dto);

    /**
    * 封装数据列表
    * @param dataList 数据列表
    * @return 封装的数据列表
    */
    List<SysRoleVo> assembleDataList(List<SysRole> dataList);

    /**
     * DTO转实体类
     * @param dto DTO
     * @return 实体类
     */
    SysRole toEntity(SysRoleDto dto);

    /**
     * 实体类转VO
     * @param entity 实体类
     * @return VO
     */
    SysRoleVo toVo(SysRole entity);
    
}
