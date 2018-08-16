package com.yangy.user.service;

import com.yangy.common.model.PageInfo;

import java.util.List;

public interface BaseService<T> {

    /**
     * 添加对象
     *
     * @param t
     * @return
     */
    T save(T t);

    /**
     * 修改对象
     *
     * @param t
     * @return
     */
    Integer update(T t);

    /**
     * 根据主键删除对象
     *
     * @param recordId
     * @return
     */
    Integer del(String recordId);

    /**
     * 根据主键集合删除对象集合
     *
     * @param recordIdList
     * @return
     */
    Integer delByIdList(List<String> recordIdList);

    /**
     * 根据id查询对象信息
     *
     * @param recordId
     * @return
     */
    T findById(String recordId);

    /**
     * 根据id集合查询对象信息集合
     *
     * @param recordIdList
     * @return
     */
    List<T> findListByIdList(List<String> recordIdList);

    /**
     * 分页查询
     *
     * @param pageInfo
     * @return
     */
    List<T> findByPage(PageInfo<T> pageInfo);

}
