package com.platform.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 数据列表数据结构
 * @author lin512100
 * @date 6/14/2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagination<T> implements Serializable {

    /**
     * 总记录数
     */
    private long total;

    /**
     * 单页记录集合
     */
    private List<T> rows;

}
