package com.platform.basic.route.service;

import java.util.List;

import com.platform.model.dto.basic.SysBlackRouteDto;
import com.platform.model.entity.basic.SysBlackRoute;
import com.platform.model.vo.basic.SysBlackRouteVo;
import com.platform.mybatis.service.BaseService;
import com.platform.mybatis.utils.PageVo;


/**
 * <p>
 * 黑名单 服务类
 * </p>
 *
 * @author lin512100
 * @since 2021-08-03
 */
public interface SysBlackRouteService extends BaseService<SysBlackRoute> {

    /**
     * 黑名单新增
     * @param dto {@link SysBlackRouteDto}
     * @return 新增主键
     */
    Long add(SysBlackRouteDto dto);

    /**
     * 黑名单删除
     * @param dto {@link SysBlackRouteDto}
     */
    void del(SysBlackRouteDto dto);

    /**
     * 黑名单修改
     * @param dto {@link SysBlackRouteDto}
     */
    void modify(SysBlackRouteDto dto);

    /**
     * 黑名单列表
     * @param dto {@link SysBlackRouteDto}
     * @return {@link SysBlackRouteVo}
     */
    PageVo<SysBlackRouteVo> list(SysBlackRouteDto dto);

    /**
    * 封装数据列表
    * @param dataList 数据列表
    * @return 封装的数据列表
    */
    List<SysBlackRouteVo> assembleDataList(List<SysBlackRoute> dataList);

    /**
     * DTO转实体类
     * @param dto DTO
     * @return 实体类
     */
    SysBlackRoute toEntity(SysBlackRouteDto dto);

    /**
     * 实体类转VO
     * @param entity 实体类
     * @return VO
     */
    SysBlackRouteVo toVo(SysBlackRoute entity);

    /**
     * 获取所有黑名单列表
     * */
    List<SysBlackRouteVo> getAllBlackRoute();
    
}
