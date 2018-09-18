package com.yangy.manage.controller;

import com.yangy.common.entity.User;
import com.yangy.common.model.PageInfo;
import com.yangy.common.model.Result;
import com.yangy.manage.service.UserService;
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
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 创建
     *
     * @param user
     * @result
     */
    @PostMapping("/save/user")
    public Result save(@RequestBody User user) {
        return new Result
<Long>().ok(userService.save(user));
    }

    /**
    * 创建
    *
    * @param user
    * @result
    */
    @PostMapping("/save/return/user")
    public Result saveAndReturn(@RequestBody User user) {
    long saveResult = userService.save(user);
    return new Result<User>().ok(userService.findById(saveResult));
    }

    /**
    * 批量创建
    *
    * @param userList
    * @result
    */
    @PostMapping("/save/list")
    public Result save(@RequestBody List<User> userList) {
    return new Result
    <Integer>().ok(userService.saveList(userList));
        }

        /**
        * 根据id删除
        *
        * @param recordId id
        * @result
        */
        @PostMapping(value = "/del/user/id")
        public Result deleteById(@RequestParam("recordId") Long recordId) {
        return new Result
        <Integer>().ok(userService.del(recordId));
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
                <Integer>().ok(userService.delByIdList(idList));
                    }

                    /**
                    * 修改
                    *
                    * @param user
                    * @result
                    */
                    @PostMapping(value = "/update/user")
                    public Result update(@RequestBody User user) {
                    return new Result
                    <Long>().ok(userService.update(user));
                        }

                        /**
                        * 修改
                        *
                        * @param user
                        * @result
                        */
                        @PostMapping(value = "/update/return/user")
                        public Result updateAndReturn(@RequestBody User user) {
                        long updateResult = userService.update(user);
                        return new Result<User>().ok(userService.findById(updateResult));
                        }

                        /**
                        * 根据id查询
                        *
                        * @param recordId
                        * @result
                        */
                        @PostMapping(value = "/find/user/id")
                        public Result findById(@RequestParam("recordId") Long recordId) {
                        return new Result<User>().ok(userService.findById(recordId));
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
                            <User>>().ok(userService.findByIdList(idList));
                            }

                            /**
                            * 条件查询
                            *
                            * @param user
                            * @result
                            */
                            @PostMapping(value = "/find/list/param")
                            public Result findByParam(@RequestBody User user) {
                            return new Result
                            <List
                            <User>>().ok(userService.findByParam(user));
                            }

                            /**
                            * 分页查询
                            *
                            * @param pageInfo
                            * @result
                            */
                            @PostMapping(value = "/find/list/page")
                            public Result findByPage(@RequestBody PageInfo<User> pageInfo) {
                            return new Result
                            <PageInfo>().ok(userService.findByPage(pageInfo));
                                }
                                }