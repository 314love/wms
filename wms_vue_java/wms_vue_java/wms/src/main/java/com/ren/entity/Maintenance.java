package com.ren.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ren
 * @since 2022-11-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Maintenance implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "type_name")
    private String typeName;

    private String phone;

    private String email;

    private Integer count;

    private String remark;


}
