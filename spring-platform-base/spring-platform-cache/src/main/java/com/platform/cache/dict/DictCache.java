package com.platform.cache.dict;

import com.platform.model.vo.basic.SysDictAllVo;
import com.platform.model.vo.basic.SysDictItemVo;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典缓存
 * @author lin512100
 * @date 2021/7/22
 */
public interface DictCache {

    /**
     * 根据Code查询字典信息
     * @param dictCode 字典Code
     * @return SysDictAllVo
     * */
    SysDictAllVo findDictByCode(String dictCode);

    /**
     * 根据Code查询字典项信息
     * @param dictCode 字典code
     * @return List
     * */
    default List<SysDictItemVo> findDictItemByCode(String dictCode){
        SysDictAllVo dictByCode = this.findDictByCode(dictCode);
        if(dictByCode == null){
            return new ArrayList<>();
        }
        return dictByCode.getSysDictItemVoList();
    }

}
