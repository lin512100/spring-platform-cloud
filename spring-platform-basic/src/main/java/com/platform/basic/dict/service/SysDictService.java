package com.platform.basic.dict.service;

import java.util.List;
import com.platform.model.entity.basic.SysDict;
import com.platform.model.vo.basic.SysDictAllVo;
import com.platform.mybatis.service.BaseService;
import com.platform.mybatis.utils.PageVo;
import com.platform.model.dto.basic.SysDictDto;
import com.platform.model.vo.basic.SysDictVo;


/**
 * <p>
 * 字典信息 服务类
 * </p>
 *
 * @author lin512100
 * @since 2021-07-22
 */
public interface SysDictService extends BaseService<SysDict> {

    /**
     * 字典信息新增
     * @param dto {@link SysDictDto}
     * @return 新增主键
     */
    Long add(SysDictDto dto);

    /**
     * 字典信息删除
     * @param dto {@link SysDictDto}
     */
    void del(SysDictDto dto);

    /**
     * 字典信息修改
     * @param dto {@link SysDictDto}
     */
    void modify(SysDictDto dto);

    /**
     * 字典信息列表
     * @param dto {@link SysDictDto}
     * @return {@link SysDictVo}
     */
    PageVo<SysDictVo> list(SysDictDto dto);

    /**
    * 封装数据列表
    * @param dataList 数据列表
    * @return 封装的数据列表
    */
    List<SysDictVo> assembleDataList(List<SysDict> dataList);

    /**
     * DTO转实体类
     * @param dto DTO
     * @return 实体类
     */
    SysDict toEntity(SysDictDto dto);

    /**
     * 实体类转VO
     * @param entity 实体类
     * @return VO
     */
    SysDictVo toVo(SysDict entity);

    /**
     * 获取所有字典信息
     * @return VO
     * */
    List<SysDictAllVo> getAllDict();
    
}
