package com.yangy.manage.service.impl;

import com.alibaba.fastjson.JSON;
import com.yangy.common.entity.RoleMenu;
import com.yangy.common.enums.ResultCode;
import com.yangy.common.exception.MyException;
import com.yangy.common.model.PageInfo;
import com.yangy.manage.dao.RoleMenuMapper;
import com.yangy.manage.service.RoleMenuService;
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
public class RoleMenuServiceImpl implements RoleMenuService {

    @Resource
    private RoleMenuMapper roleMenuMapper;

    /**
     * 添加记录
     *
     * @param record
     */
    @Override
    @Transactional
    public long save(RoleMenu record) {
        log.info("添加roleMenu -> roleMenu={}", JSON.toJSONString(record));
        try {
            roleMenuMapper.save(record);
        } catch (MyException e) {
            throw new MyException(ResultCode.SAVE_FAIL);
        }
        return record.getId();
    }

    /**
     * 批量添加记录
     *
     * @param roleMenuList
     * @return
     */
    @Override
    @Transactional
    public int saveList(List<RoleMenu> roleMenuList) {
        log.info("批量添加 roleMenu -> roleMenuList={}", JSON.toJSONString(roleMenuList));
        int saveResult = 0;
        try {
            saveResult = roleMenuMapper.saveList(roleMenuList);
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
    public long update(RoleMenu record) {
        log.info("修改roleMenu-> roleMenu={}", JSON.toJSONString(record));
        if (null == record.getId()) {
            throw new MyException(ResultCode.PARAM_ERROR);
        }
        try {
            roleMenuMapper.update(record);
        } catch (MyException e) {
            throw new MyException(ResultCode.UPDATE_FAIL);
        }
        return record.getId();
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
        log.info("删除roleMenu -> id={}", id);
        if (null == id) {
            throw new MyException(ResultCode.PARAM_ERROR);
        }
        int delResult = 0;
        try {
            delResult = roleMenuMapper.delById(id);
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
    public int del(RoleMenu record) {
        log.info("根据条件删除roleMenu -> roleMenu={}", JSON.toJSONString(record));
        if (null == record) {
            throw new MyException(ResultCode.PARAM_ERROR);
        }
        int delResult = 0;
        try {
            delResult = roleMenuMapper.delByRoleMenu(record);
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
        log.info("批量删除roleMenu -> idList={}", JSON.toJSONString(idList));
        if (CollectionUtils.isEmpty(idList)) {
            throw new MyException(ResultCode.PARAM_ERROR);
        }
        int delResult = 0;
        try {
            delResult = roleMenuMapper.delByIdList(idList);
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
    public RoleMenu findById(Long id) {
        log.info("根据主键查询roleMenu -> id={}", id);
        return null == id ? null : roleMenuMapper.findById(id);
    }

    /**
     * 查询满足条件的第一条记录
     *
     * @param record
     * @return
     */
    @Override
    @Transactional
    public RoleMenu findFirst(RoleMenu record) {
        log.info("根据条件查询roleMenu的第一条记录 -> roleMenu={}", JSON.toJSONString(record));
        return roleMenuMapper.findFirst(record);
    }

    /**
     * 根据 id 集合查询信息
     *
     * @param idList
     * @return
     */
    @Override
    public List<RoleMenu> findByIdList(List<Long> idList) {
        log.info("根据主键集合查询roleMenu -> idList={}", JSON.toJSONString(idList));
        return roleMenuMapper.findByIdList(idList);
    }

    /**
     * 根据条件查询信息
     *
     * @param record
     * @return
     */
    @Override
    public List<RoleMenu> findByParam(RoleMenu record) {
        log.info("根据条件查询roleMenu -> roleMenu={}", JSON.toJSONString(record));
        return roleMenuMapper.findByParam(record);
    }

    /**
     * 根据条件分页查询信息
     *
     * @param pageInfo
     * @return
     */
    @Override
    public PageInfo findByPage(PageInfo<RoleMenu> pageInfo) {
        log.info("分页查询roleMenu -> pageInfo={}", JSON.toJSONString(pageInfo));
        RoleMenu roleMenu = pageInfo.getData();
        List<RoleMenu> roleMenuList = roleMenuMapper.findByPage(roleMenu, pageInfo);
        int count = count(roleMenu);

        PageInfo<List<RoleMenu>> pageReturn = new PageInfo<List<RoleMenu>>();
        pageReturn.setCount(count);
        pageReturn.setPageIndex(pageInfo.getPageIndex());
        pageReturn.setPageSize(pageInfo.getPageSize());
        pageReturn.setData(roleMenuList);

        return pageReturn;
    }

    /**
     * 根据条件统计信息
     *
     * @param record
     * @return
     */
    @Override
    public int count(RoleMenu record) {
        log.info("根据条件计数 -> roleMenu={}", JSON.toJSONString(record));
        return roleMenuMapper.count(record);
    }

}