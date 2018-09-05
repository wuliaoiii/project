package com.yangy.manage.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yangy.common.model.Result;
import com.yangy.manage.entity.Role;
import com.yangy.manage.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色 前端控制器
 *
 * @author yang yang
 * @since 2018-09-04
 */
@Controller
@RequestMapping("/manage/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @PostMapping("save")
    public Result save(@RequestBody Role role) {
        boolean insert = roleService.insert(role);
        return new Result<Role>().ok(role.selectById());
    }

    @PostMapping("save/list")
    public Result saveList(@RequestBody List<Role> roleList) {
        boolean insert = roleService.insertBatch(roleList);
        return new Result<Boolean>().ok(insert);
    }

    @PostMapping("update")
    public Result update(@RequestBody Role role) {
        boolean update = roleService.updateById(role);
        return new Result<Role>().ok(role.selectById());
    }

    @PostMapping("find/by/id")
    public Result findById(@RequestParam Long recordId) {
        Role selectOne = roleService.selectById(recordId);
        return new Result<Role>().ok(selectOne);
    }

    @PostMapping("find/first")
    public Result findFirst(@RequestBody Role role) {
        EntityWrapper<Role> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(role);
        Role selectOne = roleService.selectOne(entityWrapper);
        return new Result<Role>().ok(selectOne);
    }

    @PostMapping("find/by/idList")
    public Result findByIdList(@RequestBody List<Long> idList) {
        List<Role> userList = roleService.selectBatchIds(idList);
        return new Result<List<Role>>().ok(userList);
    }

    @PostMapping("find/by/page")
    public Result findByPage(@RequestBody Role role) {
        Page<Role> rolePage = new Page<>();
        EntityWrapper<Role> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(role);
        Page<Role> rolePageDB = roleService.selectPage(rolePage, entityWrapper);
        return new Result<Page<Role>>().ok(rolePageDB);
    }

}
