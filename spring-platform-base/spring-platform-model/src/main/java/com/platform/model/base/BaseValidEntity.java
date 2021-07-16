package com.platform.model.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 逻辑删除标志
 * @author lin512100
 * @date 2021/6/28
 */
@Getter
@Setter
public class BaseValidEntity  implements Serializable {

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    protected Integer valid;

    public static final String VALID = "valid";
}
