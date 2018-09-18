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
public class Role implements Serializable {

    /**
     *角色id
     */
    private Long ruleId;

    /**
     *角色名称
     */
    private String name;

    /**
     *角色描述
     */
    private String desp;

    /**
     *创建时间
     */
    private Long ctime;

    /**
     *排序
     */
    private Integer orders;


}