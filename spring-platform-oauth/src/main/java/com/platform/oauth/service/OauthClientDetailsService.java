package com.platform.oauth.service;

import java.util.List;
import com.platform.oauth.entity.OauthClientDetails;
import com.platform.mybatis.service.BaseService;
import com.platform.mybatis.utils.PageVo;
import com.platform.oauth.pojo.dto.OauthClientDetailsDto;
import com.platform.oauth.pojo.vo.OauthClientDetailsVo;


/**
 * <p>
 * 授权认证信息 服务类
 * </p>
 *
 * @author lin512100
 * @since 2021-07-21
 */
public interface OauthClientDetailsService extends BaseService<OauthClientDetails> {

    /**
     * 授权认证信息新增
     * @param dto {@link OauthClientDetailsDto}
     * @return 新增主键
     */
    String add(OauthClientDetailsDto dto);

    /**
     * 授权认证信息删除
     * @param dto {@link OauthClientDetailsDto}
     */
    void del(OauthClientDetailsDto dto);

    /**
     * 授权认证信息修改
     * @param dto {@link OauthClientDetailsDto}
     */
    void modify(OauthClientDetailsDto dto);

    /**
     * 授权认证信息列表
     * @param dto {@link OauthClientDetailsDto}
     * @return {@link OauthClientDetailsVo}
     */
    PageVo<OauthClientDetailsVo> list(OauthClientDetailsDto dto);

    /**
    * 封装数据列表
    * @param dataList 数据列表
    * @return 封装的数据列表
    */
    List<OauthClientDetailsVo> assembleDataList(List<OauthClientDetails> dataList);

    /**
     * DTO转实体类
     * @param dto DTO
     * @return 实体类
     */
    OauthClientDetails toEntity(OauthClientDetailsDto dto);

    /**
     * 实体类转VO
     * @param entity 实体类
     * @return VO
     */
    OauthClientDetailsVo toVo(OauthClientDetails entity);
    
}
