package com.yangy.manage.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yangy.common.model.Result;
import com.yangy.manage.entity.User;
import com.yangy.manage.pojo.vo.UserVo;
import com.yangy.manage.service.RoleMenuService;
import com.yangy.manage.service.UserRoleService;
import com.yangy.manage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * 系统用户表 前端控制器
 *
 * @author yang yang
 * @since 2018-09-04
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private RoleMenuService roleMenuService;

    @PostMapping("/login")
    public Result login(@Valid UserVo userVo, HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userVo.getUsername(), userVo.getPwd());
        try {
            subject.login(token); // 登录认证
            String userName = (String) SecurityUtils.getSubject().getPrincipal();
            User build = User.builder().username(userName).build();
            EntityWrapper<User> entityWrapper = new EntityWrapper<>();
            entityWrapper.setEntity(build);
            User first = userService.selectOne(entityWrapper);


            session.setAttribute("currentUser", first);
            return new Result<User>().ok(first);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result<User>().ok(null);
    }


    /**
     * 创建
     *
     * @param user
     * @result
     */
    @PostMapping("/save/user")
    public Result save(@RequestBody User user) {
        boolean insert = userService.insert(user);
        return new Result<User>().ok(user.selectById());
    }

    @PostMapping("save/list")
    public Result saveList(@RequestBody List<User> userList) {
        boolean insert = userService.insertBatch(userList);
        return new Result<Boolean>().ok(insert);
    }

    @PostMapping("update")
    public Result update(@RequestBody User user) {
        boolean update = userService.updateById(user);
        return new Result<User>().ok(user.selectById());
    }

    @PostMapping("find/by/id")
    public Result findById(@RequestParam Long recordId) {
        User selectOne = userService.selectById(recordId);
        return new Result<User>().ok(selectOne);
    }

    @PostMapping("find/first")
    public Result findFirst(@RequestBody User user) {
        EntityWrapper<User> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(user);
        User selectOne = userService.selectOne(entityWrapper);
        return new Result<User>().ok(selectOne);
    }

    @PostMapping("find/by/idList")
    public Result findByIdList(@RequestBody List<Long> idList) {
        List<User> userList = userService.selectBatchIds(idList);
        return new Result<List<User>>().ok(userList);
    }

    @PostMapping("find/by/page")
    public Result findByPage(@RequestBody User user) {
        Page<User> userPage = new Page<>();
        EntityWrapper<User> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(user);
        Page<User> userPageDB = userService.selectPage(userPage, entityWrapper);
        return new Result<Page<User>>().ok(userPageDB);
    }

}
