package com.yangy.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yang yang
 * @email java_yangy@126.com
 * @create 2018/8/9
 * @since 1.0.0
 */
@Controller
@RequestMapping("/test")
public class TestController {


    @RequestMapping("/test")
    public String test(ModelMap modelMap) {
//        Map<String, String> map = new HashMap<>();
        modelMap.addAttribute("tableName", "user");
//        map.put("tableName", "user");
        return "test";
    }

}