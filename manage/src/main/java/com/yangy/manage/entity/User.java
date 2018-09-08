package com.yangy.manage.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 系统用户表
 *
 * @author yang yang
 * @since 2018-09-04
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户主键
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 性别(0 女 1男)
     */
    private Integer gender;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户密码
     */
    private String pwd;

    /**
     * 盐
     */
    private String salt;

    /**
     * 微信open_id
     */
    private String wechat;

    /**
     * 新浪第三方id
     */
    private String sina;

    /**
     * qq第三方id
     */
    private String qq;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    private Long ctime;

    /**
     * 启用状态(0 启用 1 锁定)
     */
    private Integer locked;

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
