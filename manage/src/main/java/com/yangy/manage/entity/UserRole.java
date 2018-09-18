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
public class UserRole implements Serializable {

    /**
     *主键
     */
    private Long id;

    /**
     *用户id
     */
    private Long userId;

    /**
     *角色id
     */
    private Long roleId;


}