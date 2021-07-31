package com.platform.basic.dict.service;

import java.util.List;
import com.platform.model.entity.basic.SysDictItem;
import com.platform.web.service.BaseService;
import com.platform.web.utils.PageVo;
import com.platform.model.dto.basic.SysDictItemDto;
import com.platform.model.vo.basic.SysDictItemVo;


/**
 * <p>
 * 字典项信息 服务类
 * </p>
 *
 * @author lin512100
 * @since 2021-07-22
 */
public interface SysDictItemService extends BaseService<SysDictItem> {

    /**
     * 字典项信息新增
     * @param dto {@link SysDictItemDto}
     * @return 新增主键
     */
    Long add(SysDictItemDto dto);

    /**
     * 字典项信息删除
     * @param dto {@link SysDictItemDto}
     */
    void del(SysDictItemDto dto);

    /**
     * 字典项信息修改
     * @param dto {@link SysDictItemDto}
     */
    void modify(SysDictItemDto dto);

    /**
     * 字典项信息列表
     * @param dto {@link SysDictItemDto}
     * @return {@link SysDictItemVo}
     */
    PageVo<SysDictItemVo> list(SysDictItemDto dto);

    /**
     * 字典项下拉列表
     * @param dictCode 字典编码
     * @return {@link SysDictItemVo}
     * */
    List<SysDictItemVo> selectItem(String dictCode);

    /**
    * 封装数据列表
    * @param dataList 数据列表
    * @return 封装的数据列表
    */
    List<SysDictItemVo> assembleDataList(List<SysDictItem> dataList);

    /**
     * DTO转实体类
     * @param dto DTO
     * @return 实体类
     */
    SysDictItem toEntity(SysDictItemDto dto);

    /**
     * 实体类转VO
     * @param entity 实体类
     * @return VO
     */
    SysDictItemVo toVo(SysDictItem entity);
    
}
