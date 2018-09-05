package com.yangy.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 *
 * @author yang yang
 * @create 2018/8/23
 * @since 1.0.0
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "sdasdasdas";
    }
}