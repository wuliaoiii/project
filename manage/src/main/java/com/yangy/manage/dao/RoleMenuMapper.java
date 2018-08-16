package com.yangy.manage.dao;

import com.yangy.common.entity.RoleMenu;
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
public interface RoleMenuMapper {


    /**
     * 添加记录
     *
     * @param record
     * @return
     */
    int save(@Param("roleMenu") RoleMenu record);

    /**
     * 批量添加记录
     *
     * @param roleMenuList
     * @return
     */
    int saveList(@Param("roleMenuList") List<RoleMenu> roleMenuList);

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
    int delByRoleMenu(@Param("roleMenu") RoleMenu record);

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
    int update(@Param("roleMenu") RoleMenu record);

    /**
     * 根据主键查询记录
     *
     * @param id
     * @return
     */
    RoleMenu findById(@Param("id") Long id);

    /**
     * 查询满足条件的第一条记录
     *
     * @param record
     * @return
     */
    RoleMenu findFirst(@Param("roleMenu") RoleMenu record);

    /**
     * 根据id集合查询记录集合
     *
     * @param idList
     * @return
     */
    List<RoleMenu> findByIdList(@Param("idList") List<Long> idList);

    /**
     * 条件查询记录集合
     *
     * @param record
     * @return
     */
    List<RoleMenu> findByParam(@Param("roleMenu") RoleMenu record);

    /**
     * 分页查询
     *
     * @param pageInfo
     * @return
     */
    List<RoleMenu> findByPage(@Param("roleMenu") RoleMenu roleMenu, @Param("pageInfo") PageInfo pageInfo);

    /**
     * 条件统计
     *
     * @param record
     * @return
     */
    int count(@Param("roleMenu") RoleMenu record);

}