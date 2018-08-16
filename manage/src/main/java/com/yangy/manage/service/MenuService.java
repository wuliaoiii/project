package com.yangy.manage.service;

import com.yangy.common.entity.Menu;
import com.yangy.common.model.PageInfo;

import java.util.List;

/**
 * 描述：接口
 *
 * @author yangy
 * @date 2018/08/10
 */
public interface MenuService {

    /**
     * 添加记录
     *
     * @param record
     * @return
     */
    long save(Menu record);

    /**
     * 批量添加记录
     *
     * @param menuList
     * @return
     */
    int saveList(List<Menu> menuList);

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
    int del(Menu record);

    /**
     * 根据主键集合删除记录
     *
     * @param idList
     * @return
     */
    int delByIdList(List<Long> idList);

    /**
     * 修改记录
     *
     * @param record
     * @return
     */
    long update(Menu record);

    /**
     * 根据主键查询记录
     *
     * @param id
     * @return
     */
    Menu findById(Long id);

    /**
     * 查询满足条件的第一条记录
     *
     * @param record
     * @return
     */
    Menu findFirst(Menu record);

    /**
     * 根据id集合查询记录集合
     *
     * @param idList
     * @return
     */
    List<Menu> findByIdList(List<Long> idList);

    /**
     * 条件查询记录集合
     *
     * @param record
     * @return
     */
    List<Menu> findByParam(Menu record);

    /**
     * 分页查询
     *
     * @param pageInfo
     * @return
     */
    PageInfo findByPage(PageInfo<Menu> pageInfo);

    /**
     * 条件统计
     *
     * @param record
     * @return
     */
    int count(Menu record);
}
