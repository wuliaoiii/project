package com.yangy.manage.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 角色
 *
 * @author yang yang
 * @since 2018-09-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role")
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    @TableId(value = "rule_id", type = IdType.AUTO)
    private Long ruleId;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色描述
     */
    private String desp;

    /**
     * 创建时间
     */
    private Long ctime;

    /**
     * 排序
     */
    private Integer orders;

    @Override
    protected Serializable pkVal() {
        return this.ruleId;
    }

}
