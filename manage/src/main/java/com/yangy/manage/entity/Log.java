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
public class Log implements Serializable {

    /**
     *日志id
     */
    private Long logId;

    /**
     *用户名称
     */
    private String username;

    /**
     *用户主键
     */
    private Long userId;

    /**
     *描述
     */
    private String desp;

    /**
     *操作ip
     */
    private String ip;

    /**
     *请求方式
     */
    private String type;

    /**
     *请求方法
     */
    private String method;

    /**
     *权限
     */
    private String menu;

    /**
     *请求路径
     */
    private String url;

    /**
     *操作时间
     */
    private Long ctime;


}