package com.platform.user.service;

import java.util.List;
import com.platform.model.entity.user.SysAccount;
import com.platform.model.vo.OauthUserVo;
import com.platform.web.service.BaseService;
import com.platform.web.utils.PageVo;
import com.platform.model.dto.user.SysAccountDto;
import com.platform.model.vo.user.SysAccountVo;


/**
 * <p>
 * 账户信息 服务类
 * </p>
 *
 * @author lin512100
 * @since 2021-07-22
 */
public interface SysAccountService extends BaseService<SysAccount> {

    /**
     * 账户信息新增
     * @param dto {@link SysAccountDto}
     * @return 新增主键
     */
    Long add(SysAccountDto dto);

    /**
     * 账户信息删除
     * @param dto {@link SysAccountDto}
     */
    void del(SysAccountDto dto);

    /**
     * 账户信息修改
     * @param dto {@link SysAccountDto}
     */
    void modify(SysAccountDto dto);

    /**
     * 账户信息列表
     * @param dto {@link SysAccountDto}
     * @return {@link SysAccountVo}
     */
    PageVo<SysAccountVo> list(SysAccountDto dto);

    /**
    * 封装数据列表
    * @param dataList 数据列表
    * @return 封装的数据列表
    */
    List<SysAccountVo> assembleDataList(List<SysAccount> dataList);

    /**
     * DTO转实体类
     * @param dto DTO
     * @return 实体类
     */
    SysAccount toEntity(SysAccountDto dto);

    /**
     * 实体类转VO
     * @param entity 实体类
     * @return VO
     */
    SysAccountVo toVo(SysAccount entity);

    /**
     * 获取用户权限信息
     * @param username 登录用户名
     * @return OauthUserVo
     * */
    OauthUserVo getOauthUserVo(String username);

}
