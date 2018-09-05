package com.yangy.common.base;

import com.yangy.common.model.PageInfo;

import java.util.List;

public interface BaseService<Model> {

    /**
     * 添加记录
     *
     * @param model
     * @return
     */
    Integer save(Model model);

    /**
     * 批量添加记录
     *
     * @param model
     * @return
     */
    Integer saveList(List<Model> model);

    /**
     * 根据主键删除记录
     *
     * @param id
     * @return
     */
    Integer del(Long id);

    /**
     * 根据条件删除记录
     *
     * @param model
     * @return
     */
    Integer del(Model model);

    /**
     * 根据主键集合删除记录
     *
     * @param idList
     * @return
     */
    Integer delByIdList(List<Long> idList);

    /**
     * 修改记录
     *
     * @param model
     * @return
     */
    Integer update(Model model);

    /**
     * 根据主键查询记录
     *
     * @param model
     * @return
     */
    Model findById(Model model);

    /**
     * 根据条件查询第一条记录
     *
     * @param model
     * @return
     */
    Model findOne(Model model);

    /**
     * 根据id集合查询记录集合
     *
     * @param idList
     * @return
     */
    List<Model> findByIdList(List<Long> idList);

    /**
     * 条件查询记录集合
     *
     * @param model
     * @return
     */
    List<Model> findByParam(Model model);

    /**
     * 分页查询
     *
     * @param pageInfo
     * @return
     */
    PageInfo findByPage(PageInfo<Model> pageInfo);

    /**
     * 条件统计
     *
     * @param model
     * @return
     */
    Integer count(Model model);

}
