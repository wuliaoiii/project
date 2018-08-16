package com.yangy.common.annotation;


import java.lang.annotation.*;

/*
 * 注解的作用域
 * CONSTRUCTOR 构造器的声明
 * FIELD 域声明
 * LOCAL_VARIABLE 局部变量声明
 * METHOD 方法声明
 * PACKAGE 包声明
 * PARAMETER 参数说明
 * TYPE 类 接口(包括注解类型) 或者enum声明
 * */
@Target({ElementType.TYPE})

/*
 * 在什么级别保存该注解
 * SOURCE 注解将被编译器丢弃
 * CLASS 在class文件中可用 会被VM丢弃
 * RUNTIME VM运行期间保留注解 可以通过反射机制读取注解的信息
 * */
@Retention(RetentionPolicy.RUNTIME)

/*
 * 注解会包含在JAVA DOC中
 * */
@Documented
public @interface BaseService {

}
