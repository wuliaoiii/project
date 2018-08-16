package com.yangy.manage.controller;

import com.yangy.common.entity.UserRole;
import com.yangy.common.model.PageInfo;
import com.yangy.common.model.Result;
import com.yangy.manage.service.UserRoleService;
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
@RequestMapping("/userRole")
public class UserRoleController {

    @Resource
    private UserRoleService userRoleService;

    /**
     * 创建
     *
     * @param userRole
     * @result
     */
    @PostMapping("/save/userRole")
    public Result save(@RequestBody UserRole userRole) {
        return new Result<Long>().ok(userRoleService.save(userRole));
    }

    /**
     * 创建
     *
     * @param userRole
     * @result
     */
    @PostMapping("/save/return/userRole")
    public Result saveAndReturn(@RequestBody UserRole userRole) {
        long saveResult = userRoleService.save(userRole);
        return new Result<UserRole>().ok(userRoleService.findById(saveResult));
    }

    /**
     * 批量创建
     *
     * @param userRoleList
     * @result
     */
    @PostMapping("/save/list")
    public Result save(@RequestBody List<UserRole> userRoleList) {
        return new Result<Integer>().ok(userRoleService.saveList(userRoleList));
    }

    /**
     * 根据id删除
     *
     * @param recordId id
     * @result
     */
    @PostMapping(value = "/del/userRole/id")
    public Result deleteById(@RequestParam("recordId") Long recordId) {
        return new Result<Integer>().ok(userRoleService.del(recordId));
    }

    /**
     * 根据id集合删除
     *
     * @param idList
     * @result
     */
    @PostMapping(value = "/del/list/ids")
    public Result deleteById(@RequestParam("idList") List<Long> idList) {
        return new Result<Integer>().ok(userRoleService.delByIdList(idList));
    }

    /**
     * 修改
     *
     * @param userRole
     * @result
     */
    @PostMapping(value = "/update/userRole")
    public Result update(@RequestBody UserRole userRole) {
        return new Result<Long>().ok(userRoleService.update(userRole));
    }

    /**
     * 修改
     *
     * @param userRole
     * @result
     */
    @PostMapping(value = "/update/return/userRole")
    public Result updateAndReturn(@RequestBody UserRole userRole) {
        long updateResult = userRoleService.update(userRole);
        return new Result<UserRole>().ok(userRoleService.findById(updateResult));
    }

    /**
     * 根据id查询
     *
     * @param recordId
     * @result
     */
    @PostMapping(value = "/find/userRole/id")
    public Result findById(@RequestParam("recordId") Long recordId) {
        return new Result<UserRole>().ok(userRoleService.findById(recordId));
    }

    /**
     * 根据id集合查询
     *
     * @param idList
     * @result
     */
    @PostMapping(value = "/find/list/ids")
    public Result findByIdList(@RequestParam("idList") List<Long> idList) {
        return new Result<List<UserRole>>().ok(userRoleService.findByIdList(idList));
    }

    /**
     * 条件查询
     *
     * @param userRole
     * @result
     */
    @PostMapping(value = "/find/list/param")
    public Result findByParam(@RequestBody UserRole userRole) {
        return new Result<List<UserRole>>().ok(userRoleService.findByParam(userRole));
    }

    /**
     * 分页查询
     *
     * @param pageInfo
     * @result
     */
    @PostMapping(value = "/find/list/page")
    public Result findByPage(@RequestBody PageInfo<UserRole> pageInfo) {
        return new Result<PageInfo>().ok(userRoleService.findByPage(pageInfo));
    }
}