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
public class RoleMenu implements Serializable {

    /**
     *
     */
    private Long id;

    /**
     *角色id
     */
    private Long roleId;

    /**
     *权限id
     */
    private Long menuId;


}