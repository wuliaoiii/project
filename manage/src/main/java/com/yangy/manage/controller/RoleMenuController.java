package com.yangy.manage.controller;

import com.yangy.common.entity.RoleMenu;
import com.yangy.common.model.PageInfo;
import com.yangy.common.model.Result;
import com.yangy.manage.service.RoleMenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述：控制层
 *
 * @author yangy
 * @date 2018/08/10
 */
@RestController
@RequestMapping("/roleMenu")
public class RoleMenuController {

    @Resource
    private RoleMenuService roleMenuService;

    /**
     * 创建
     *
     * @param roleMenu
     * @result
     */
    @PostMapping("/save/roleMenu")
    public Result save(@RequestBody RoleMenu roleMenu) {
        return new Result<Long>().ok(roleMenuService.save(roleMenu));
    }

    /**
     * 创建
     *
     * @param roleMenu
     * @result
     */
    @PostMapping("/save/return/roleMenu")
    public Result saveAndReturn(@RequestBody RoleMenu roleMenu) {
        long saveResult = roleMenuService.save(roleMenu);
        return new Result<RoleMenu>().ok(roleMenuService.findById(saveResult));
    }

    /**
     * 批量创建
     *
     * @param roleMenuList
     * @result
     */
    @PostMapping("/save/list")
    public Result save(@RequestBody List<RoleMenu> roleMenuList) {
        return new Result<Integer>().ok(roleMenuService.saveList(roleMenuList));
    }

    /**
     * 根据id删除
     *
     * @param recordId id
     * @result
     */
    @PostMapping(value = "/del/roleMenu/id")
    public Result deleteById(@RequestParam("recordId") Long recordId) {
        return new Result<Integer>().ok(roleMenuService.del(recordId));
    }

    /**
     * 根据id集合删除
     *
     * @param idList
     * @result
     */
    @PostMapping(value = "/del/list/ids")
    public Result deleteById(@RequestParam("idList") List<Long> idList) {
        return new Result<Integer>().ok(roleMenuService.delByIdList(idList));
    }

    /**
     * 修改
     *
     * @param roleMenu
     * @result
     */
    @PostMapping(value = "/update/roleMenu")
    public Result update(@RequestBody RoleMenu roleMenu) {
        return new Result<Long>().ok(roleMenuService.update(roleMenu));
    }

    /**
     * 修改
     *
     * @param roleMenu
     * @result
     */
    @PostMapping(value = "/update/return/roleMenu")
    public Result updateAndReturn(@RequestBody RoleMenu roleMenu) {
        long updateResult = roleMenuService.update(roleMenu);
        return new Result<RoleMenu>().ok(roleMenuService.findById(updateResult));
    }

    /**
     * 根据id查询
     *
     * @param recordId
     * @result
     */
    @PostMapping(value = "/find/roleMenu/id")
    public Result findById(@RequestParam("recordId") Long recordId) {
        return new Result<RoleMenu>().ok(roleMenuService.findById(recordId));
    }

    /**
     * 根据id集合查询
     *
     * @param idList
     * @result
     */
    @PostMapping(value = "/find/list/ids")
    public Result findByIdList(@RequestParam("idList") List<Long> idList) {
        return new Result<List<RoleMenu>>().ok(roleMenuService.findByIdList(idList));
    }

    /**
     * 条件查询
     *
     * @param roleMenu
     * @result
     */
    @PostMapping(value = "/find/list/param")
    public Result findByParam(@RequestBody RoleMenu roleMenu) {
        return new Result<List<RoleMenu>>().ok(roleMenuService.findByParam(roleMenu));
    }

    /**
     * 分页查询
     *
     * @param pageInfo
     * @result
     */
    @PostMapping(value = "/find/list/page")
    public Result findByPage(@RequestBody PageInfo<RoleMenu> pageInfo) {
        return new Result<PageInfo>().ok(roleMenuService.findByPage(pageInfo));
    }
}