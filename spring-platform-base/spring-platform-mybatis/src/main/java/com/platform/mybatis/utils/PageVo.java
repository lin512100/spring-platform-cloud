package com.platform.mybatis.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页
 * @author lin512100
 * @date 2021/6/17
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "分页返回数据")
public class PageVo<T> {

    @ApiModelProperty(value = "每页大小")
    private Integer pageSize;

    @ApiModelProperty(value = "总条数")
    private Long totalCount = 0L;

    @ApiModelProperty(value = "当前页")
    private Integer pageNo = 1;

    @ApiModelProperty(value = "总页数")
    private Integer totalPage = 0;

    @ApiModelProperty(value = "分页数据")
    private List<T> records = new ArrayList<>();

    @ApiModelProperty(value = "额外返回数据")
    private Object extraData;

    @ApiModelProperty(value = "最后一条数据的ID")
    private Integer lastId;

    public PageVo(Integer pageSize, Integer pageNo, Long totalCount, List<T> records) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = new BigDecimal(totalCount)
            .divide(new BigDecimal(pageSize), 0, BigDecimal.ROUND_UP).intValue();
        this.records = records;
    }

    public PageVo(Integer pageSize, Integer pageNo, Integer totalCount, List<T> records, Object extraData) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = Long.valueOf(totalCount);
        this.totalPage = new BigDecimal(totalCount)
            .divide(new BigDecimal(pageSize), 0, BigDecimal.ROUND_UP).intValue();
        this.records = records;
        this.setExtraData(extraData);
    }

}
