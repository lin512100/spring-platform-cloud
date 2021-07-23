package com.platform.cache.dict;

import com.platform.model.vo.basic.SysDictAllVo;
import com.platform.model.vo.basic.SysDictItemVo;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.util.CollectionUtils;

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
     */
    SysDictAllVo findDictByCode(String dictCode);

    /**
     * 根据Code查询字典项信息
     * @param dictCode 字典code
     * @return List
     */
    default List<SysDictItemVo> findDictItemByCode(String dictCode) {
        SysDictAllVo dictByCode = this.findDictByCode(dictCode);
        if (dictByCode == null) {
            return new ArrayList<>();
        }
        return dictByCode.getSysDictItemVoList();
    }

    /**
     * 查询字典项信息
     * @param dictCode 字典
     * @param value    字典值
     * @return 字典项信息
     */
    default SysDictItemVo findDictItemByCodeAndValue(String dictCode, String value) {
        List<SysDictItemVo> dictItemByCode = findDictItemByCode(dictCode);
        return dictItemByCode.stream()
            .filter(item -> item.getItemValue().equals(value))
            .findFirst().orElse(null);
    }

    /**
     * 查询字典项信息
     * @param dictAllVo 字典
     * @return 字典项信息
     */
    default SysDictItemVo findDictItemByDictAndValue(SysDictAllVo dictAllVo, String value) {
        if(dictAllVo == null || CollectionUtils.isEmpty(dictAllVo.getSysDictItemVoList())){
            return null;
        }
        return dictAllVo.getSysDictItemVoList().stream()
            .filter(item -> item.getItemValue().equals(value))
            .findFirst().orElse(null);
    }

    /**
     * 校验枚举值是否存在
     * @param dictCode 字典
     * @param value    字典值
     * @return boolean
     */
    default boolean verifyDict(String dictCode, String value) {
        SysDictItemVo dictItemVo = findDictItemByCodeAndValue(dictCode, value);
        return dictItemVo != null;
    }

    /**
     * 校验枚举值是否存在
     * @param dictCode 字典
     * @param value    字典值
     */
    default void veryDictStatus(String dictCode,String value){
        // 校验枚举值是否存在
        SysDictAllVo dictAllVo = findDictByCode(dictCode);
        if(dictAllVo == null){
            throw new PersistenceException(String.format("字典编码%s不存在", value));
        }
        SysDictItemVo dictItemByDictAndValue = findDictItemByDictAndValue(dictAllVo, value);
        if(dictItemByDictAndValue == null){
            throw new PersistenceException(String.format("%s无此选项", dictAllVo.getDictName()));
        }
    }

    /**
     * 查询字典值信息
     * @param dictCode 字典
     * @param value    字典值
     * @return 字典项信息
     */
    default String findDictItemDescByCodeAndValue(String dictCode, String value) {
        SysDictItemVo dictItemVo = findDictItemByCodeAndValue(dictCode, value);
        return (dictItemVo == null) ? "未知" : dictItemVo.getItemDescription();
    }

}
