package com.yangy.manage.pojo.vo;

import com.yangy.manage.entity.Menu;
import com.yangy.manage.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.TreeSet;

/**
 * 系统用户展示model
 *
 * @author yang yang
 * @email java_yangy@126.com
 * @create 2018/8/17
 * @since 1.0.0
 */

@Getter
@Setter
public class UserVo {

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 验证码
     */
    private String verifyCode;

    /**
     * 用户头像地址
     */
    private String avatar;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 性别(0 女 1男)
     */
    private String gender;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 盐
     */
    private String salt;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 角色列表
     */
    private List<Role> roleList;

    /**
     * 菜单列表
     */
    private TreeSet<Menu> menuTree;

}