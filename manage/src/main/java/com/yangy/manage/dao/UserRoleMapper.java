package com.yangy.manage.dao;

import com.yangy.common.entity.UserRole;
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
public interface UserRoleMapper {


    /**
     * 添加记录
     *
     * @param record
     * @return
     */
    int save(@Param("userRole") UserRole record);

    /**
     * 批量添加记录
     *
     * @param userRoleList
     * @return
     */
    int saveList(@Param("userRoleList") List<UserRole> userRoleList);

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
    int delByUserRole(@Param("userRole") UserRole record);

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
    int update(@Param("userRole") UserRole record);

    /**
     * 根据主键查询记录
     *
     * @param id
     * @return
     */
    UserRole findById(@Param("id") Long id);

    /**
     * 查询满足条件的第一条记录
     *
     * @param record
     * @return
     */
    UserRole findFirst(@Param("userRole") UserRole record);

    /**
     * 根据id集合查询记录集合
     *
     * @param idList
     * @return
     */
    List<UserRole> findByIdList(@Param("idList") List<Long> idList);

    /**
     * 条件查询记录集合
     *
     * @param record
     * @return
     */
    List<UserRole> findByParam(@Param("userRole") UserRole record);

    /**
     * 分页查询
     *
     * @param pageInfo
     * @return
     */
    List<UserRole> findByPage(@Param("userRole") UserRole userRole, @Param("pageInfo") PageInfo pageInfo);

    /**
     * 条件统计
     *
     * @param record
     * @return
     */
    int count(@Param("userRole") UserRole record);

}