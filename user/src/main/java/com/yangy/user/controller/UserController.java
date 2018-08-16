package com.yangy.user.controller;

import com.yangy.common.annotation.Validation;
import com.yangy.common.model.Result;
import com.yangy.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @Validation
    @PostMapping("/test")
    public String test(@RequestParam(name = "name", required = false) String name) {

        System.out.println(name);
        System.out.println(StringUtils.isBlank(name));
        return name;
    }

    @PostMapping("/save")
    public Result<User> save(@RequestBody User user) {
        User save = userService.save(user);
        return null;
    }
}
