package com.ren.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class Recordtwo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 货品id
     */
    private Integer maintenance;

    /**
     * 申请人id
     */
    @TableField("userId")
    private Integer userid;

    /**
     * 操作人id
     */
    @TableField("adminId")
    private Integer adminid;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 操作时间
     */
    private LocalDateTime createtime;

    /**
     * 备注
     */
    private String remark;


}
