package com.yangy.manage.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yangy.common.model.Result;
import com.yangy.manage.entity.UserRole;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户角色 前端控制器
 *
 * @author yang yang
 * @since 2018-09-04
 */
@Controller
@RequestMapping("/manage/userRole")
public class UserRoleController {

    @Resource
    private UserRoleService userRoleService;

    @PostMapping("save")
    public Result save(@RequestBody UserRole userRole) {
        boolean insert = userRoleService.insert(userRole);
        return new Result<UserRole>().ok(userRole.selectById());
    }

    @PostMapping("save/list")
    public Result saveList(@RequestBody List<UserRole> userRoleList) {
        boolean insert = userRoleService.insertBatch(userRoleList);
        return new Result<Boolean>().ok(insert);
    }

    @PostMapping("update")
    public Result update(@RequestBody UserRole userRole) {
        boolean update = userRoleService.updateById(userRole);
        return new Result<UserRole>().ok(userRole.selectById());
    }

    @PostMapping("find/by/id")
    public Result findById(@RequestParam Long recordId) {
        UserRole selectOne = userRoleService.selectById(recordId);
        return new Result<UserRole>().ok(selectOne);
    }

    @PostMapping("find/first")
    public Result findFirst(@RequestBody UserRole userRole) {
        EntityWrapper<UserRole> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(userRole);
        UserRole selectOne = userRoleService.selectOne(entityWrapper);
        return new Result<UserRole>().ok(selectOne);
    }

    @PostMapping("find/by/idList")
    public Result findByIdList(@RequestBody List<Long> idList) {
        List<UserRole> userList = userRoleService.selectBatchIds(idList);
        return new Result<List<UserRole>>().ok(userList);
    }

    @PostMapping("find/by/page")
    public Result findByPage(@RequestBody UserRole userRole) {
        Page<UserRole> userRolePage = new Page<>();
        EntityWrapper<UserRole> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(userRole);
        Page<UserRole> userRolePageDB = userRoleService.selectPage(userRolePage, entityWrapper);
        return new Result<Page<UserRole>>().ok(userRolePageDB);
    }

}
