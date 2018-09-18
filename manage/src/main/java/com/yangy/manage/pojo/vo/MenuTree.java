package com.yangy.manage.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单树
 *
 * @author yang yang
 * @create 2018/9/15
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuTree implements Serializable, Comparable<MenuTree> {

    private static final long serialVersionUID = 1L;

    /**
     * 权限表id
     */
    private Long menuId;

    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型(0目录 1 菜单2 按钮)
     */
    private Integer type;

    /**
     * 权限制
     */
    private String value;

    /**
     * 路径
     */
    private String url;

    /**
     * 图标
     */
    private String icon;

    /**
     * 状态(0 启用 1 禁用)
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Long ctime;

    /**
     * 排序
     */
    private Integer orders;

    private List<MenuTree> children;

    @Override
    public int compareTo(MenuTree menuTree) {
        if (null == menuTree || null == menuTree.getParentId()) {
            return 0;
        }
        if (menuTree.getParentId().equals(this.parentId)) {
            return 0;
        } else if (menuTree.getParentId() > this.parentId) {
            return 1;
        } else if (menuTree.getParentId() < this.parentId) {
            return -1;
        }
        return 0;
    }

}