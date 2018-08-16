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
@TableName("sys_user_role")
public class UserRole implements Serializable {

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 角色id
     */
    @TableField("role_id")
    private Long roleId;


}