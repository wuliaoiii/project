package com.yangy.manage.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yangy.common.model.Result;
import com.yangy.manage.entity.Menu;
import com.yangy.manage.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限表 前端控制器
 *
 * @author yang yang
 * @since 2018-09-04
 */
@Controller
@RequestMapping("/manage/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    @PostMapping("save")
    public Result save(@RequestBody Menu menu) {
        boolean insert = menuService.insert(menu);
        return new Result<Menu>().ok(menu.selectById());
    }

    @PostMapping("save/list")
    public Result saveList(@RequestBody List<Menu> menuList) {
        boolean insert = menuService.insertBatch(menuList);
        return new Result<Boolean>().ok(insert);
    }

    @PostMapping("update")
    public Result update(@RequestBody Menu menu) {
        boolean update = menuService.updateById(menu);
        return new Result<Menu>().ok(menu.selectById());
    }

    @PostMapping("find/by/id")
    public Result findById(@RequestParam Long recordId) {
        Menu selectOne = menuService.selectById(recordId);
        return new Result<Menu>().ok(selectOne);
    }

    @PostMapping("find/first")
    public Result findFirst(@RequestBody Menu menu) {
        EntityWrapper<Menu> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(menu);
        Menu selectOne = menuService.selectOne(entityWrapper);
        return new Result<Menu>().ok(selectOne);
    }

    @PostMapping("find/by/idList")
    public Result findByIdList(@RequestBody List<Long> idList) {
        List<Menu> userList = menuService.selectBatchIds(idList);
        return new Result<List<Menu>>().ok(userList);
    }

    @PostMapping("find/by/page")
    public Result findByPage(@RequestBody Menu menu) {
        Page<Menu> menuPage = new Page<>();
        EntityWrapper<Menu> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(menu);
        Page<Menu> menuPageDB = menuService.selectPage(menuPage, entityWrapper);
        return new Result<Page<Menu>>().ok(menuPageDB);
    }

}
