package com.yangy.common.annotation;

import lombok.Data;
import lombok.ToString;

import java.lang.annotation.Annotation;

/**
 * 方法参数类
 *
 * @author michael.bai
 * @date 2016年12月28日
 */
@Data
@ToString
public class Param {

    //简单名字
    private String simpleName;

    //名字
    private String name;

    //类型
    private Class<?> type;

    //值
    private Object value;

    //注解
    private Annotation anno;

}

