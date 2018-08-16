package com.yangy.common.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
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
@TableName("sys_log")
public class Log implements Serializable {

    /**
     * 日志id
     */
    @TableId(type = IdType.AUTO)
    private Long logId;

    /**
     * 用户名称
     */
    @TableField("username")
    private String username;

    /**
     * 用户主键
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 描述
     */
    @TableField("desp")
    private String desp;

    /**
     * 操作ip
     */
    @TableField("ip")
    private String ip;

    /**
     * 请求方式
     */
    @TableField("type")
    private String type;

    /**
     * 请求方法
     */
    @TableField("method")
    private String method;

    /**
     * 权限
     */
    @TableField("menu")
    private String menu;

    /**
     * 请求路径
     */
    @TableField("url")
    private String url;

    /**
     * 操作时间
     */
    @TableField("ctime")
    private Long ctime;


}