package com.yangy.manage.dao;

import com.yangy.common.entity.User;
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
public interface UserMapper {


    /**
     * 添加记录
     *
     * @param record
     * @return
     */
    int save(User record);

    /**
     * 批量添加记录
     *
     * @param userList
     * @return
     */
    int saveList(@Param("userList") List<User> userList);

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
    int delByUser(@Param("user") User record);

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
    int update(@Param("user") User record);

    /**
     * 根据主键查询记录
     *
     * @param id
     * @return
     */
    User findById(@Param("id") Long id);

    /**
     * 查询满足条件的第一条记录
     *
     * @param record
     * @return
     */
    User findFirst(@Param("user") User record);

    /**
     * 根据id集合查询记录集合
     *
     * @param idList
     * @return
     */
    List<User> findByIdList(@Param("idList") List<Long> idList);

    /**
     * 条件查询记录集合
     *
     * @param record
     * @return
     */
    List<User> findByParam(@Param("user") User record);

    /**
     * 分页查询
     *
     * @param pageInfo
     * @return
     */
    List<User> findByPage(@Param("user") User user, @Param("pageInfo") PageInfo pageInfo);

    /**
     * 条件统计
     *
     * @param record
     * @return
     */
    int count(@Param("user") User record);

}