package com.yangy.manage.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 描述：模型
 * @author yangy
 * @date 2018/09/18
 */
@Data
@NoArgsConstructor
public class User implements Serializable {

    /**
     *用户主键
     */
    private Long userId;

    /**
     *用户名
     */
    private String username;

    /**
     *性别(0 女 1男)
     */
    private Integer gender;

    /**
     *头像
     */
    private String avatar;

    /**
     *用户密码
     */
    private String pwd;

    /**
     *盐
     */
    private String salt;

    /**
     *微信open_id
     */
    private String wechat;

    /**
     *新浪第三方id
     */
    private String sina;

    /**
     *qq第三方id
     */
    private String qq;

    /**
     *用户手机号
     */
    private String phone;

    /**
     *用户邮箱
     */
    private String email;

    /**
     *创建时间
     */
    private Long ctime;

    /**
     *启用状态(0 启用 1 锁定)
     */
    private Integer locked;


}