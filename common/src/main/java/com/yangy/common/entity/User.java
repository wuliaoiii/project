package com.yangy.common.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
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
@TableName("sys_user")
public class User implements Serializable {

    /**
     * 用户主键
     */
    @TableId(type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 性别(0 女 1男)
     */
    @TableField("gender")
    private Integer gender;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 用户密码
     */
    @TableField("pwd")
    private String pwd;

    /**
     * 盐
     */
    @TableField("salt")
    private String salt;

    /**
     * 微信open_id
     */
    @TableField("wechat")
    private String wechat;

    /**
     * 新浪第三方id
     */
    @TableField("sina")
    private String sina;

    /**
     * qq第三方id
     */
    @TableField("qq")
    private String qq;

    /**
     * 用户手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 用户邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 创建时间
     */
    @TableField("ctime")
    private Long ctime;

    /**
     * 启用状态(0 启用 1 锁定)
     */
    @TableField("locked")
    private Integer locked;


}