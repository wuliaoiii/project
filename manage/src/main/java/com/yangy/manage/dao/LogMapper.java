package com.yangy.manage.dao;

import com.yangy.common.entity.Log;
import com.yangy.common.model.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 描述：
 *
 * @author yangy
 * @date 2018/08/10
 */
@Mapper
public interface LogMapper {


    /**
     * 添加记录
     *
     * @param record
     * @return
     */
    int save(@Param("log") Log record);

    /**
     * 批量添加记录
     *
     * @param logList
     * @return
     */
    int saveList(@Param("logList") List<Log> logList);

    /**
     * 根据主键删除记录
     *
     * @param id
     * @return
     */
    int delById(@Param("id") Long id);

    /**
     * 根据条件删除记录
     *
     * @param record
     * @return
     */
    int delByLog(@Param("log") Log record);

    /**
     * 根据主键集合删除记录
     *
     * @param idList
     * @return
     */
    int delByIdList(@Param("idList") List<Long> idList);

    /**
     * 修改记录
     *
     * @param record
     * @return
     */
    int update(@Param("log") Log record);

    /**
     * 根据主键查询记录
     *
     * @param id
     * @return
     */
    Log findById(@Param("id") Long id);

    /**
     * 查询满足条件的第一条记录
     *
     * @param record
     * @return
     */
    Log findFirst(@Param("log") Log record);

    /**
     * 根据id集合查询记录集合
     *
     * @param idList
     * @return
     */
    List<Log> findByIdList(@Param("idList") List<Long> idList);

    /**
     * 条件查询记录集合
     *
     * @param record
     * @return
     */
    List<Log> findByParam(@Param("log") Log record);

    /**
     * 分页查询
     *
     * @param pageInfo
     * @return
     */
    List<Log> findByPage(@Param("log") Log log, @Param("pageInfo") PageInfo pageInfo);

    /**
     * 条件统计
     *
     * @param record
     * @return
     */
    int count(@Param("log") Log record);

}