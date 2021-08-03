package com.platform.basic.route.service;

import java.util.List;

import com.platform.model.dto.basic.SysWhiteRouteDto;
import com.platform.model.entity.basic.SysWhiteRoute;
import com.platform.model.vo.basic.SysWhiteRouteVo;
import com.platform.mybatis.service.BaseService;
import com.platform.mybatis.utils.PageVo;


/**
 * <p>
 * 白名单 服务类
 * </p>
 * @author lin512100
 * @since 2021-08-03
 */
public interface SysWhiteRouteService extends BaseService<SysWhiteRoute> {

    /**
     * 白名单新增
     * @param dto {@link SysWhiteRouteDto}
     * @return 新增主键
     */
    Long add(SysWhiteRouteDto dto);

    /**
     * 白名单删除
     * @param dto {@link SysWhiteRouteDto}
     */
    void del(SysWhiteRouteDto dto);

    /**
     * 白名单修改
     * @param dto {@link SysWhiteRouteDto}
     */
    void modify(SysWhiteRouteDto dto);

    /**
     * 白名单列表
     * @param dto {@link SysWhiteRouteDto}
     * @return {@link SysWhiteRouteVo}
     */
    PageVo<SysWhiteRouteVo> list(SysWhiteRouteDto dto);

    /**
     * 封装数据列表
     * @param dataList 数据列表
     * @return 封装的数据列表
     */
    List<SysWhiteRouteVo> assembleDataList(List<SysWhiteRoute> dataList);

    /**
     * DTO转实体类
     * @param dto DTO
     * @return 实体类
     */
    SysWhiteRoute toEntity(SysWhiteRouteDto dto);

    /**
     * 实体类转VO
     * @param entity 实体类
     * @return VO
     */
    SysWhiteRouteVo toVo(SysWhiteRoute entity);

    /**
     * 获取所有白名单列表
     */
    List<SysWhiteRouteVo> getAllWhiteRoute();

}
