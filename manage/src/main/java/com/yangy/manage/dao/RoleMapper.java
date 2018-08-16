package com.yangy.manage.dao;

import com.yangy.common.entity.Role;
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
public interface RoleMapper {


    /**
     * 添加记录
     *
     * @param record
     * @return
     */
    int save(@Param("role") Role record);

    /**
     * 批量添加记录
     *
     * @param roleList
     * @return
     */
    int saveList(@Param("roleList") List<Role> roleList);

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
    int delByRole(@Param("role") Role record);

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
    int update(@Param("role") Role record);

    /**
     * 根据主键查询记录
     *
     * @param id
     * @return
     */
    Role findById(@Param("id") Long id);

    /**
     * 查询满足条件的第一条记录
     *
     * @param record
     * @return
     */
    Role findFirst(@Param("role") Role record);

    /**
     * 根据id集合查询记录集合
     *
     * @param idList
     * @return
     */
    List<Role> findByIdList(@Param("idList") List<Long> idList);

    /**
     * 条件查询记录集合
     *
     * @param record
     * @return
     */
    List<Role> findByParam(@Param("role") Role record);

    /**
     * 分页查询
     *
     * @param pageInfo
     * @return
     */
    List<Role> findByPage(@Param("role") Role role, @Param("pageInfo") PageInfo pageInfo);

    /**
     * 条件统计
     *
     * @param record
     * @return
     */
    int count(@Param("role") Role record);

}