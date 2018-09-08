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

/**
 * 操作日志
 *
 * @author yang yang
 * @since 2018-09-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_log")
public class Log extends Model<Log> {

    private static final long serialVersionUID = 1L;

    /**
     * 日志id
     */
    @TableId(value = "log_id", type = IdType.AUTO)
    private Long logId;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户主键
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 描述
     */
    private String desp;

    /**
     * 操作ip
     */
    private String ip;

    /**
     * 请求方式
     */
    private String type;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 权限
     */
    private String menu;

    /**
     * 请求路径
     */
    private String url;

    /**
     * 操作时间
     */
    private Long ctime;

    @Override
    protected Serializable pkVal() {
        return this.logId;
    }

}
