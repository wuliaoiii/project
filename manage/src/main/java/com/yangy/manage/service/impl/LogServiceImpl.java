package com.yangy.manage.service.impl;

import com.alibaba.fastjson.JSON;
import com.yangy.common.entity.Log;
import com.yangy.common.enums.ResultCode;
import com.yangy.common.exception.MyException;
import com.yangy.common.model.PageInfo;
import com.yangy.manage.dao.LogMapper;
import com.yangy.manage.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
/**
 * 描述： 服务实现层
 *
 * @author yangy
 * @date 2018/09/18
 */
@Service
@Slf4j
public class LogServiceImpl implements LogService {

    @Resource
    private LogMapper logMapper;

    /**
     * 添加记录
     *
     * @param record
     */
    @Override
    @Transactional
    public long save(Log record){
        log.info("添加log -> log={}",JSON.toJSONString(record));
        try {
logMapper.save(record);
        } catch (MyException e) {
            throw new MyException(ResultCode.SAVE_FAIL);
        }
        return record.getLogId();
    }

    /**
     * 批量添加记录
     *
     * @param logList
     * @return
     */
    @Override
    @Transactional
    public int saveList(List<Log> logList){
        log.info("批量添加 log -> logList={}",JSON.toJSONString(logList));
        int saveResult = 0;
        try {
            saveResult = logMapper.saveList(logList);
        } catch (MyException e) {
            throw new MyException(ResultCode.SAVE_FAIL);
        }
        return saveResult;
    }

    /**
     * 修改记录
     *
     * @param record
     * @return
     */
    @Override
    @Transactional
    public long update(Log record){
        log.info("修改log-> log={}",JSON.toJSONString(record));
        if(null == record.getLogId()){
            throw new MyException(ResultCode.PARAM_ERROR);
        }
        try {
logMapper.update(record);
        } catch (MyException e) {
            throw new MyException(ResultCode.UPDATE_FAIL);
        }
        return record.getLogId();
    }

    /**
     *  根据主键删除信息
     * @param id
     * @return
     */
    @Override
    @Transactional
    public int del(Long id){
        log.info("删除log -> id={}",id);
        if(null == id){
            throw new MyException(ResultCode.PARAM_ERROR);
        }
        int delResult = 0;
        try {
            delResult = logMapper.delById(id);
        } catch (Exception e) {
            throw new MyException(ResultCode.DEL_FAIL);
        }
        return delResult;
    }

    /**
     * 根据条件删除信息
     *
     * @param record
     * @return
     */
    @Override
    @Transactional
    public int del(Log record){
        log.info("根据条件删除log -> log={}",JSON.toJSONString(record));
        if(null == record){
            throw new MyException(ResultCode.PARAM_ERROR);
        }
        int delResult = 0;
        try {
            delResult = logMapper.delByLog(record);
        } catch (Exception e) {
            throw new MyException(ResultCode.DEL_FAIL);
        }
        return delResult;
    }

    /**
     * 根据主键集合删除信息
     *
     * @param idList
     * @return
     */
    @Override
    @Transactional
    public int delByIdList(List
<Long> idList){
    log.info("批量删除log -> idList={}",JSON.toJSONString(idList));
    if(CollectionUtils.isEmpty(idList)){
    throw new MyException(ResultCode.PARAM_ERROR);
    }
    int delResult = 0;
    try {
    delResult = logMapper.delByIdList(idList);
    } catch (Exception e) {
    throw new MyException(ResultCode.DEL_FAIL);
    }
    return delResult;
    }

    /**
    * 根据id 查询信息
    *
    * @param id
    * @return
    */
    @Override
    @Transactional
    public Log findById(Long id){
    log.info("根据主键查询log -> id={}",id);
    return null == id ? null : logMapper.findById(id);
    }

    /**
    * 查询满足条件的第一条记录
    *
    * @param record
    * @return
    */
    @Override
    @Transactional
    public Log findFirst(Log record){
    log.info("根据条件查询log的第一条记录 -> log={}",JSON.toJSONString(record));
    return logMapper.findFirst(record);
    }

    /**
    * 根据 id 集合查询信息
    *
    * @param idList
    * @return
    */
    @Override
    public List<Log> findByIdList(List
    <Long> idList){
        log.info("根据主键集合查询log -> idList={}",JSON.toJSONString(idList));
        return logMapper.findByIdList(idList);
        }

        /**
        * 根据条件查询信息
        *
        * @param record
        * @return
        */
        @Override
        public List<Log> findByParam(Log record) {
        log.info("根据条件查询log -> log={}",JSON.toJSONString(record));
        return logMapper.findByParam(record);
        }

        /**
        * 根据条件分页查询信息
        *
        * @param pageInfo
        * @return
        */
        @Override
        public PageInfo findByPage(PageInfo<Log> pageInfo) {
        log.info("分页查询log -> pageInfo={}",JSON.toJSONString(pageInfo));
    Log log = pageInfo.getData();
        List<Log> logList = logMapper.findByPage(log, pageInfo);
        int count = count(log);

        PageInfo
        <List
        <Log>> pageReturn = new PageInfo
        <List
        <Log>>();
        pageReturn.setCount(count);
        pageReturn.setPageIndex(pageInfo.getPageIndex());
        pageReturn.setPageSize(pageInfo.getPageSize());
        pageReturn.setData(logList);

        return pageReturn;
        }

        /**
        * 根据条件统计信息
        *
        * @param record
        * @return
        */
        @Override
        public int count(Log record) {
        log.info("根据条件计数 -> log={}", JSON.toJSONString(record));
        return logMapper.count(record);
        }
        }