package com.yangy.manage.controller;

import com.yangy.common.entity.Role;
import com.yangy.common.model.PageInfo;
import com.yangy.common.model.Result;
import com.yangy.manage.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述：控制层
 *
 * @author yangy
 * @date 2018/09/18
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 创建
     *
     * @param role
     * @result
     */
    @PostMapping("/save/role")
    public Result save(@RequestBody Role role) {
        return new Result
<Long>().ok(roleService.save(role));
    }

    /**
    * 创建
    *
    * @param role
    * @result
    */
    @PostMapping("/save/return/role")
    public Result saveAndReturn(@RequestBody Role role) {
    long saveResult = roleService.save(role);
    return new Result<Role>().ok(roleService.findById(saveResult));
    }

    /**
    * 批量创建
    *
    * @param roleList
    * @result
    */
    @PostMapping("/save/list")
    public Result save(@RequestBody List<Role> roleList) {
    return new Result
    <Integer>().ok(roleService.saveList(roleList));
        }

        /**
        * 根据id删除
        *
        * @param recordId id
        * @result
        */
        @PostMapping(value = "/del/role/id")
        public Result deleteById(@RequestParam("recordId") Long recordId) {
        return new Result
        <Integer>().ok(roleService.del(recordId));
            }

            /**
            * 根据id集合删除
            *
            * @param idList
            * @result
            */
            @PostMapping(value = "/del/list/ids")
            public Result deleteById(@RequestParam("idList") List
            <Long> idList) {
                return new Result
                <Integer>().ok(roleService.delByIdList(idList));
                    }

                    /**
                    * 修改
                    *
                    * @param role
                    * @result
                    */
                    @PostMapping(value = "/update/role")
                    public Result update(@RequestBody Role role) {
                    return new Result
                    <Long>().ok(roleService.update(role));
                        }

                        /**
                        * 修改
                        *
                        * @param role
                        * @result
                        */
                        @PostMapping(value = "/update/return/role")
                        public Result updateAndReturn(@RequestBody Role role) {
                        long updateResult = roleService.update(role);
                        return new Result<Role>().ok(roleService.findById(updateResult));
                        }

                        /**
                        * 根据id查询
                        *
                        * @param recordId
                        * @result
                        */
                        @PostMapping(value = "/find/role/id")
                        public Result findById(@RequestParam("recordId") Long recordId) {
                        return new Result<Role>().ok(roleService.findById(recordId));
                        }

                        /**
                        * 根据id集合查询
                        *
                        * @param idList
                        * @result
                        */
                        @PostMapping(value = "/find/list/ids")
                        public Result findByIdList(@RequestParam("idList") List
                        <Long> idList) {
                            return new Result
                            <List
                            <Role>>().ok(roleService.findByIdList(idList));
                            }

                            /**
                            * 条件查询
                            *
                            * @param role
                            * @result
                            */
                            @PostMapping(value = "/find/list/param")
                            public Result findByParam(@RequestBody Role role) {
                            return new Result
                            <List
                            <Role>>().ok(roleService.findByParam(role));
                            }

                            /**
                            * 分页查询
                            *
                            * @param pageInfo
                            * @result
                            */
                            @PostMapping(value = "/find/list/page")
                            public Result findByPage(@RequestBody PageInfo<Role> pageInfo) {
                            return new Result
                            <PageInfo>().ok(roleService.findByPage(pageInfo));
                                }
                                }