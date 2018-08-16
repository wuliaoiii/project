package com.yangy.manage.service.impl;

import com.alibaba.fastjson.JSON;
import com.yangy.common.entity.Menu;
import com.yangy.common.enums.ResultCode;
import com.yangy.common.exception.MyException;
import com.yangy.common.model.PageInfo;
import com.yangy.manage.dao.MenuMapper;
import com.yangy.manage.service.MenuService;
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
 * @date 2018/08/10
 */
@Service
@Slf4j
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    /**
     * 添加记录
     *
     * @param record
     */
    @Override
    @Transactional
    public long save(Menu record) {
        log.info("添加menu -> menu={}", JSON.toJSONString(record));
        try {
            menuMapper.save(record);
        } catch (MyException e) {
            throw new MyException(ResultCode.SAVE_FAIL);
        }
        return record.getMenuId();
    }

    /**
     * 批量添加记录
     *
     * @param menuList
     * @return
     */
    @Override
    @Transactional
    public int saveList(List<Menu> menuList) {
        log.info("批量添加 menu -> menuList={}", JSON.toJSONString(menuList));
        int saveResult = 0;
        try {
            saveResult = menuMapper.saveList(menuList);
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
    public long update(Menu record) {
        log.info("修改menu-> menu={}", JSON.toJSONString(record));
        if (null == record.getMenuId()) {
            throw new MyException(ResultCode.PARAM_ERROR);
        }
        try {
            menuMapper.update(record);
        } catch (MyException e) {
            throw new MyException(ResultCode.UPDATE_FAIL);
        }
        return record.getMenuId();
    }

    /**
     * 根据主键删除信息
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public int del(Long id) {
        log.info("删除menu -> id={}", id);
        if (null == id) {
            throw new MyException(ResultCode.PARAM_ERROR);
        }
        int delResult = 0;
        try {
            delResult = menuMapper.delById(id);
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
    public int del(Menu record) {
        log.info("根据条件删除menu -> menu={}", JSON.toJSONString(record));
        if (null == record) {
            throw new MyException(ResultCode.PARAM_ERROR);
        }
        int delResult = 0;
        try {
            delResult = menuMapper.delByMenu(record);
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
    public int delByIdList(List<Long> idList) {
        log.info("批量删除menu -> idList={}", JSON.toJSONString(idList));
        if (CollectionUtils.isEmpty(idList)) {
            throw new MyException(ResultCode.PARAM_ERROR);
        }
        int delResult = 0;
        try {
            delResult = menuMapper.delByIdList(idList);
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
    public Menu findById(Long id) {
        log.info("根据主键查询menu -> id={}", id);
        return null == id ? null : menuMapper.findById(id);
    }

    /**
     * 查询满足条件的第一条记录
     *
     * @param record
     * @return
     */
    @Override
    @Transactional
    public Menu findFirst(Menu record) {
        log.info("根据条件查询menu的第一条记录 -> menu={}", JSON.toJSONString(record));
        return menuMapper.findFirst(record);
    }

    /**
     * 根据 id 集合查询信息
     *
     * @param idList
     * @return
     */
    @Override
    public List<Menu> findByIdList(List<Long> idList) {
        log.info("根据主键集合查询menu -> idList={}", JSON.toJSONString(idList));
        return menuMapper.findByIdList(idList);
    }

    /**
     * 根据条件查询信息
     *
     * @param record
     * @return
     */
    @Override
    public List<Menu> findByParam(Menu record) {
        log.info("根据条件查询menu -> menu={}", JSON.toJSONString(record));
        return menuMapper.findByParam(record);
    }

    /**
     * 根据条件分页查询信息
     *
     * @param pageInfo
     * @return
     */
    @Override
    public PageInfo findByPage(PageInfo<Menu> pageInfo) {
        log.info("分页查询menu -> pageInfo={}", JSON.toJSONString(pageInfo));
        Menu menu = pageInfo.getData();
        List<Menu> menuList = menuMapper.findByPage(menu, pageInfo);
        int count = count(menu);

        PageInfo<List<Menu>> pageReturn = new PageInfo<List<Menu>>();
        pageReturn.setCount(count);
        pageReturn.setPageIndex(pageInfo.getPageIndex());
        pageReturn.setPageSize(pageInfo.getPageSize());
        pageReturn.setData(menuList);

        return pageReturn;
    }

    /**
     * 根据条件统计信息
     *
     * @param record
     * @return
     */
    @Override
    public int count(Menu record) {
        log.info("根据条件计数 -> menu={}", JSON.toJSONString(record));
        return menuMapper.count(record);
    }

}