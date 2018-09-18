package com.yangy.manage.controller;

import com.yangy.common.entity.Log;
import com.yangy.common.model.PageInfo;
import com.yangy.common.model.Result;
import com.yangy.manage.service.LogService;
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
@RequestMapping("/log")
public class LogController {

    @Resource
    private LogService logService;

    /**
     * 创建
     *
     * @param log
     * @result
     */
    @PostMapping("/save/log")
    public Result save(@RequestBody Log log) {
        return new Result
<Long>().ok(logService.save(log));
    }

    /**
    * 创建
    *
    * @param log
    * @result
    */
    @PostMapping("/save/return/log")
    public Result saveAndReturn(@RequestBody Log log) {
    long saveResult = logService.save(log);
    return new Result<Log>().ok(logService.findById(saveResult));
    }

    /**
    * 批量创建
    *
    * @param logList
    * @result
    */
    @PostMapping("/save/list")
    public Result save(@RequestBody List<Log> logList) {
    return new Result
    <Integer>().ok(logService.saveList(logList));
        }

        /**
        * 根据id删除
        *
        * @param recordId id
        * @result
        */
        @PostMapping(value = "/del/log/id")
        public Result deleteById(@RequestParam("recordId") Long recordId) {
        return new Result
        <Integer>().ok(logService.del(recordId));
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
                <Integer>().ok(logService.delByIdList(idList));
                    }

                    /**
                    * 修改
                    *
                    * @param log
                    * @result
                    */
                    @PostMapping(value = "/update/log")
                    public Result update(@RequestBody Log log) {
                    return new Result
                    <Long>().ok(logService.update(log));
                        }

                        /**
                        * 修改
                        *
                        * @param log
                        * @result
                        */
                        @PostMapping(value = "/update/return/log")
                        public Result updateAndReturn(@RequestBody Log log) {
                        long updateResult = logService.update(log);
                        return new Result<Log>().ok(logService.findById(updateResult));
                        }

                        /**
                        * 根据id查询
                        *
                        * @param recordId
                        * @result
                        */
                        @PostMapping(value = "/find/log/id")
                        public Result findById(@RequestParam("recordId") Long recordId) {
                        return new Result<Log>().ok(logService.findById(recordId));
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
                            <Log>>().ok(logService.findByIdList(idList));
                            }

                            /**
                            * 条件查询
                            *
                            * @param log
                            * @result
                            */
                            @PostMapping(value = "/find/list/param")
                            public Result findByParam(@RequestBody Log log) {
                            return new Result
                            <List
                            <Log>>().ok(logService.findByParam(log));
                            }

                            /**
                            * 分页查询
                            *
                            * @param pageInfo
                            * @result
                            */
                            @PostMapping(value = "/find/list/page")
                            public Result findByPage(@RequestBody PageInfo<Log> pageInfo) {
                            return new Result
                            <PageInfo>().ok(logService.findByPage(pageInfo));
                                }
                                }