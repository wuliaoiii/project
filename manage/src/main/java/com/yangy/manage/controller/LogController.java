package com.yangy.manage.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yangy.common.model.Result;
import com.yangy.manage.entity.Log;
import com.yangy.manage.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * 操作日志 前端控制器
 *
 * @author yang yang
 * @since 2018-09-04
 */
@Controller
@RequestMapping("/manage/log")
public class LogController {

    @Resource
    private LogService logService;

    @PostMapping("save")
    public Result save(@RequestBody Log log) {
        boolean insert = logService.insert(log);
        return new Result<Log>().ok(log.selectById());
    }

    @PostMapping("save/list")
    public Result saveList(@RequestBody List<Log> logList) {
        boolean insert = logService.insertBatch(logList);
        return new Result<Boolean>().ok(insert);
    }

    @PostMapping("update")
    public Result update(@RequestBody Log log) {
        boolean update = logService.updateById(log);
        return new Result<Log>().ok(log.selectById());
    }

    @PostMapping("find/by/id")
    public Result findById(@RequestParam Long recordId) {
        Log selectOne = logService.selectById(recordId);
        return new Result<Log>().ok(selectOne);
    }

    @PostMapping("find/first")
    public Result findFirst(@RequestBody Log log) {
        EntityWrapper<Log> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(log);
        Log selectOne = logService.selectOne(entityWrapper);
        return new Result<Log>().ok(selectOne);
    }

    @PostMapping("find/by/idList")
    public Result findByIdList(@RequestBody List<Long> idList) {
        List<Log> userList = logService.selectBatchIds(idList);
        return new Result<List<Log>>().ok(userList);
    }

    @PostMapping("find/by/page")
    public Result findByPage(@RequestBody Log log) {
        Page<Log> logPage = new Page<>();
        EntityWrapper<Log> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(log);
        Page<Log> logPageDB = logService.selectPage(logPage, entityWrapper);
        return new Result<Page<Log>>().ok(logPageDB);
    }

}
