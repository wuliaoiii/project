package com.yangy.user.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yangy.common.model.Result;
import com.yangy.user.entity.SysUser;
import com.yangy.user.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统用户表 前端控制器
 *
 * @author yang yang
 * @since 2018-08-30
 */
@Controller
@RequestMapping("/user")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @PostMapping("save")
    public Result save(@RequestBody SysUser sysUser) {
        boolean insert = sysUserService.insert(sysUser);
        return new Result<SysUser>().ok(sysUser.selectById());
    }

    @PostMapping("save/list")
    public Result saveList(@RequestBody List<SysUser> sysUserList) {
        boolean insert = sysUserService.insertBatch(sysUserList);
        return new Result<Boolean>().ok(insert);
    }

    @PostMapping("update")
    public Result update(@RequestBody SysUser sysUser) {
        boolean update = sysUserService.updateById(sysUser);
        return new Result<SysUser>().ok(sysUser.selectById());
    }

    @PostMapping("find/by/id")
    public Result findById(@RequestParam Long userId) {
        SysUser selectOne = sysUserService.selectById(userId);
        return new Result<SysUser>().ok(selectOne);
    }

    @PostMapping("find/first")
    public Result findFirst(@RequestBody SysUser sysUser) {
        EntityWrapper<SysUser> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(sysUser);
        SysUser selectOne = sysUserService.selectOne(entityWrapper);
        return new Result<SysUser>().ok(selectOne);
    }

    @PostMapping("find/by/idList")
    public Result findByIdList(@RequestBody List<Long> idList) {
        List<SysUser> userList = sysUserService.selectBatchIds(idList);
        return new Result<List<SysUser>>().ok(userList);
    }

    @PostMapping("find/by/page")
    public Result findByPage(@RequestBody SysUser sysUser) {
        Page<SysUser> userPage = new Page<>();
        EntityWrapper<SysUser> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(sysUser);
        Page<SysUser> sysUserPage = sysUserService.selectPage(userPage, entityWrapper);
        return new Result<Page<SysUser>>().ok(sysUserPage);
    }


}
