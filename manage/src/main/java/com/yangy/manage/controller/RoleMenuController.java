package com.yangy.manage.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yangy.common.model.Result;
import com.yangy.manage.entity.RoleMenu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色权限表 前端控制器
 *
 * @author yang yang
 * @since 2018-09-04
 */
@Controller
@RequestMapping("/manage/roleMenu")
public class RoleMenuController {

    @Resource
    private RoleMenuService roleMenuService;

    @PostMapping("save")
    public Result save(@RequestBody RoleMenu roleMenu) {
        boolean insert = roleMenuService.insert(roleMenu);
        return new Result<RoleMenu>().ok(roleMenu.selectById());
    }

    @PostMapping("save/list")
    public Result saveList(@RequestBody List<RoleMenu> roleMenuList) {
        boolean insert = roleMenuService.insertBatch(roleMenuList);
        return new Result<Boolean>().ok(insert);
    }

    @PostMapping("update")
    public Result update(@RequestBody RoleMenu roleMenu) {
        boolean update = roleMenuService.updateById(roleMenu);
        return new Result<RoleMenu>().ok(roleMenu.selectById());
    }

    @PostMapping("find/by/id")
    public Result findById(@RequestParam Long recordId) {
        RoleMenu selectOne = roleMenuService.selectById(recordId);
        return new Result<RoleMenu>().ok(selectOne);
    }

    @PostMapping("find/first")
    public Result findFirst(@RequestBody RoleMenu roleMenu) {
        EntityWrapper<RoleMenu> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(roleMenu);
        RoleMenu selectOne = roleMenuService.selectOne(entityWrapper);
        return new Result<RoleMenu>().ok(selectOne);
    }

    @PostMapping("find/by/idList")
    public Result findByIdList(@RequestBody List<Long> idList) {
        List<RoleMenu> userList = roleMenuService.selectBatchIds(idList);
        return new Result<List<RoleMenu>>().ok(userList);
    }

    @PostMapping("find/by/page")
    public Result findByPage(@RequestBody RoleMenu roleMenu) {
        Page<RoleMenu> roleMenuPage = new Page<>();
        EntityWrapper<RoleMenu> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(roleMenu);
        Page<RoleMenu> roleMenuPageDB = roleMenuService.selectPage(roleMenuPage, entityWrapper);
        return new Result<Page<RoleMenu>>().ok(roleMenuPageDB);
    }

}
