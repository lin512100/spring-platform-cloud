package com.platform.user.service;

import java.util.List;
import com.platform.model.entity.user.SysUser;
import com.platform.model.vo.OauthUserVo;
import com.platform.web.service.BaseService;
import com.platform.web.utils.PageVo;
import com.platform.model.dto.user.SysUserDto;
import com.platform.model.vo.user.SysUserVo;


/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author lin512100
 * @since 2021-07-22
 */
public interface SysUserService extends BaseService<SysUser> {

    /**
     * 用户实名认证
     * @param dto {@link SysUserDto}
     * @return 新增主键
     */
    Long authentication(SysUserDto dto);

    /**
     * 用户状态修改
     * @param dto {@link SysUserDto}
     */
    void modifyStatus(SysUserDto dto);

    /**
     * 用户信息列表
     * @param dto {@link SysUserDto}
     * @return {@link SysUserVo}
     */
    PageVo<SysUserVo> list(SysUserDto dto);

    /**
    * 封装数据列表
    * @param dataList 数据列表
    * @return 封装的数据列表
    */
    List<SysUserVo> assembleDataList(List<SysUser> dataList);

    /**
     * DTO转实体类
     * @param dto DTO
     * @return 实体类
     */
    SysUser toEntity(SysUserDto dto);

    /**
     * 实体类转VO
     * @param entity 实体类
     * @return VO
     */
    SysUserVo toVo(SysUser entity);
}
