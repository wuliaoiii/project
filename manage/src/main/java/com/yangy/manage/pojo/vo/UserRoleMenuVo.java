package com.yangy.manage.pojo.vo;

import com.yangy.manage.entity.Menu;
import com.yangy.manage.entity.Role;

import java.util.List;
import java.util.TreeSet;

/**
 * 菜单vo
 *
 * @author yang yang
 * @create 2018/9/6
 * @since 1.0.0
 */
public class UserRoleMenuVo {


    /*
     * 菜单类型
     * 菜单地址
     * 菜单icon
     * 菜单
     * */

    private String userId;
    private List<Long> roleIdList;
    private List<Role> roleList;
    private List<Long> menuIdList;

    private TreeSet<Menu> menuSet;


}