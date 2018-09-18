package com.yangy.manage.controller;

import com.yangy.common.entity.Menu;
import com.yangy.common.model.PageInfo;
import com.yangy.common.model.Result;
import com.yangy.manage.service.MenuService;
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
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    /**
     * 创建
     *
     * @param menu
     * @result
     */
    @PostMapping("/save/menu")
    public Result save(@RequestBody Menu menu) {
        return new Result
<Long>().ok(menuService.save(menu));
    }

    /**
    * 创建
    *
    * @param menu
    * @result
    */
    @PostMapping("/save/return/menu")
    public Result saveAndReturn(@RequestBody Menu menu) {
    long saveResult = menuService.save(menu);
    return new Result<Menu>().ok(menuService.findById(saveResult));
    }

    /**
    * 批量创建
    *
    * @param menuList
    * @result
    */
    @PostMapping("/save/list")
    public Result save(@RequestBody List<Menu> menuList) {
    return new Result
    <Integer>().ok(menuService.saveList(menuList));
        }

        /**
        * 根据id删除
        *
        * @param recordId id
        * @result
        */
        @PostMapping(value = "/del/menu/id")
        public Result deleteById(@RequestParam("recordId") Long recordId) {
        return new Result
        <Integer>().ok(menuService.del(recordId));
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
                <Integer>().ok(menuService.delByIdList(idList));
                    }

                    /**
                    * 修改
                    *
                    * @param menu
                    * @result
                    */
                    @PostMapping(value = "/update/menu")
                    public Result update(@RequestBody Menu menu) {
                    return new Result
                    <Long>().ok(menuService.update(menu));
                        }

                        /**
                        * 修改
                        *
                        * @param menu
                        * @result
                        */
                        @PostMapping(value = "/update/return/menu")
                        public Result updateAndReturn(@RequestBody Menu menu) {
                        long updateResult = menuService.update(menu);
                        return new Result<Menu>().ok(menuService.findById(updateResult));
                        }

                        /**
                        * 根据id查询
                        *
                        * @param recordId
                        * @result
                        */
                        @PostMapping(value = "/find/menu/id")
                        public Result findById(@RequestParam("recordId") Long recordId) {
                        return new Result<Menu>().ok(menuService.findById(recordId));
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
                            <Menu>>().ok(menuService.findByIdList(idList));
                            }

                            /**
                            * 条件查询
                            *
                            * @param menu
                            * @result
                            */
                            @PostMapping(value = "/find/list/param")
                            public Result findByParam(@RequestBody Menu menu) {
                            return new Result
                            <List
                            <Menu>>().ok(menuService.findByParam(menu));
                            }

                            /**
                            * 分页查询
                            *
                            * @param pageInfo
                            * @result
                            */
                            @PostMapping(value = "/find/list/page")
                            public Result findByPage(@RequestBody PageInfo<Menu> pageInfo) {
                            return new Result
                            <PageInfo>().ok(menuService.findByPage(pageInfo));
                                }
                                }