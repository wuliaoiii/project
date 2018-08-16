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
@TableName("sys_menu")
public class Menu implements Serializable {

    /**
     * 权限表id
     */
    @TableId
    private Long menuId;

    /**
     * 父级id
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 类型(0目录 1 菜单2 按钮)
     */
    @TableField("type")
    private Integer type;

    /**
     * 权限制
     */
    @TableField("value")
    private String value;

    /**
     * 路径
     */
    @TableField("url")
    private String url;

    /**
     * 图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 状态(0 启用 1 禁用)
     */
    @TableField("status")
    private Integer status;

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