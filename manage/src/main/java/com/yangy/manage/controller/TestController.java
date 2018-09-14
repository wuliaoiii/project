package com.yangy.manage.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 *
 * @author yang yang
 * @create 2018/9/14
 * @since 1.0.0
 */
@RestController
@Configuration
@ConfigurationProperties(prefix = "test")
@RefreshScope
@RequestMapping("test")
public class TestController {

    @Value("${test.msg}")
    private String msg;

    @GetMapping("get")
    public String getMsg() {
        System.out.println(msg);
        return msg;
    }

}