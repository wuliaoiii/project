package com.yangy.common.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 描述：模型
 *
 * @author yangy
 * @date 2018/08/10
 */
@Data
@NoArgsConstructor
@TableName("sys_role")
public class Role implements Serializable {

    /**
     * 角色id
     */
    @TableId
    private Long ruleId;

    /**
     * 角色名称
     */
    @TableField("name")
    private String name;

    /**
     * 角色描述
     */
    @TableField("desp")
    private String desp;

    /**
     * 创建时间
     */
    @TableField("ctime")
    private Long ctime;

    /**
     * 排序
     */
    @TableField("orders")
    private Integer orders;


}