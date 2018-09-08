package com.yangy.manage.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 权限表
 *
 * @author yang yang
 * @since 2018-09-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_menu")
public class Menu extends Model<Menu> implements Comparable<Menu> {

    private static final long serialVersionUID = 1L;

    /**
     * 权限表id
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    /**
     * 父级id
     */
    @TableField("parent_id")
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

    private List<Menu> children;

    @Override
    protected Serializable pkVal() {
        return this.menuId;
    }

    @Override
    public int compareTo(Menu menu) {
        if (null == menu || null == menu.getParentId()) {
            return 0;
        }
        if (menu.getParentId().equals(this.parentId)) {
            return 0;
        } else if (menu.getParentId() > this.parentId) {
            return 1;
        } else if (menu.getParentId() < this.parentId) {
            return -1;
        }
        return 0;
    }
}
