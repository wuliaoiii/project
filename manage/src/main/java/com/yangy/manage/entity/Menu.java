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
public class Menu implements Serializable {

    /**
     *权限表id
     */
    private Long menuId;

    /**
     *父级id
     */
    private Long parentId;

    /**
     *名称
     */
    private String name;

    /**
     *类型(0目录 1 菜单2 按钮)
     */
    private Integer type;

    /**
     *权限制
     */
    private String value;

    /**
     *路径
     */
    private String url;

    /**
     *图标
     */
    private String icon;

    /**
     *状态(0 启用 1 禁用)
     */
    private Integer status;

    /**
     *创建时间
     */
    private Long ctime;

    /**
     *排序
     */
    private Integer orders;


}