package com.yangy.manage.service;

import com.yangy.common.entity.Log;
import com.yangy.common.model.PageInfo;

import java.util.List;

/**
 * 描述：接口
 *
 * @author yangy
 * @date 2018/09/18
 */
public interface LogService{

    /**
     * 添加记录
     *
     * @param record
     * @return
     */
    long save(Log record);

    /**
     * 批量添加记录
     *
     * @param logList
     * @return
     */
    int saveList(List<Log> logList);

    /**
     * 根据主键删除记录
     *
     * @param id
     * @return
     */
    int del(Long id);

    /**
     * 根据条件删除记录
     *
     * @param record
     * @return
     */
    int del(Log record);

    /**
     * 根据主键集合删除记录
     *
     * @param idList
     * @return
     */
    int delByIdList(List
<Long> idList);

    /**
    * 修改记录
    *
    * @param record
    * @return
    */
    long update(Log record);

    /**
    * 根据主键查询记录
    *
    * @param id
    * @return
    */
Log findById(Long id);

    /**
    * 查询满足条件的第一条记录
    *
    * @param record
    * @return
    */
Log findFirst(Log record);

    /**
    * 根据id集合查询记录集合
    *
    * @param idList
    * @return
    */
    List<Log> findByIdList(List
    <Long> idList);

        /**
        * 条件查询记录集合
        *
        * @param record
        * @return
        */
        List<Log> findByParam(Log record);

        /**
        * 分页查询
        *
        * @param pageInfo
        * @return
        */
        PageInfo findByPage(PageInfo<Log> pageInfo);

        /**
        * 条件统计
        *
        * @param record
        * @return
        */
        int count(Log record);
        }
