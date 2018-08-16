package com.yangy.manage.service.impl;

import com.alibaba.fastjson.JSON;
import com.yangy.common.entity.UserRole;
import com.yangy.common.enums.ResultCode;
import com.yangy.common.exception.MyException;
import com.yangy.common.model.PageInfo;
import com.yangy.manage.dao.UserRoleMapper;
import com.yangy.manage.service.UserRoleService;
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
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private UserRoleMapper userRoleMapper;

    /**
     * 添加记录
     *
     * @param record
     */
    @Override
    @Transactional
    public long save(UserRole record) {
        log.info("添加userRole -> userRole={}", JSON.toJSONString(record));
        try {
            userRoleMapper.save(record);
        } catch (MyException e) {
            throw new MyException(ResultCode.SAVE_FAIL);
        }
        return record.getId();
    }

    /**
     * 批量添加记录
     *
     * @param userRoleList
     * @return
     */
    @Override
    @Transactional
    public int saveList(List<UserRole> userRoleList) {
        log.info("批量添加 userRole -> userRoleList={}", JSON.toJSONString(userRoleList));
        int saveResult = 0;
        try {
            saveResult = userRoleMapper.saveList(userRoleList);
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
    public long update(UserRole record) {
        log.info("修改userRole-> userRole={}", JSON.toJSONString(record));
        if (null == record.getId()) {
            throw new MyException(ResultCode.PARAM_ERROR);
        }
        try {
            userRoleMapper.update(record);
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
        log.info("删除userRole -> id={}", id);
        if (null == id) {
            throw new MyException(ResultCode.PARAM_ERROR);
        }
        int delResult = 0;
        try {
            delResult = userRoleMapper.delById(id);
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
    public int del(UserRole record) {
        log.info("根据条件删除userRole -> userRole={}", JSON.toJSONString(record));
        if (null == record) {
            throw new MyException(ResultCode.PARAM_ERROR);
        }
        int delResult = 0;
        try {
            delResult = userRoleMapper.delByUserRole(record);
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
        log.info("批量删除userRole -> idList={}", JSON.toJSONString(idList));
        if (CollectionUtils.isEmpty(idList)) {
            throw new MyException(ResultCode.PARAM_ERROR);
        }
        int delResult = 0;
        try {
            delResult = userRoleMapper.delByIdList(idList);
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
    public UserRole findById(Long id) {
        log.info("根据主键查询userRole -> id={}", id);
        return null == id ? null : userRoleMapper.findById(id);
    }

    /**
     * 查询满足条件的第一条记录
     *
     * @param record
     * @return
     */
    @Override
    @Transactional
    public UserRole findFirst(UserRole record) {
        log.info("根据条件查询userRole的第一条记录 -> userRole={}", JSON.toJSONString(record));
        return userRoleMapper.findFirst(record);
    }

    /**
     * 根据 id 集合查询信息
     *
     * @param idList
     * @return
     */
    @Override
    public List<UserRole> findByIdList(List<Long> idList) {
        log.info("根据主键集合查询userRole -> idList={}", JSON.toJSONString(idList));
        return userRoleMapper.findByIdList(idList);
    }

    /**
     * 根据条件查询信息
     *
     * @param record
     * @return
     */
    @Override
    public List<UserRole> findByParam(UserRole record) {
        log.info("根据条件查询userRole -> userRole={}", JSON.toJSONString(record));
        return userRoleMapper.findByParam(record);
    }

    /**
     * 根据条件分页查询信息
     *
     * @param pageInfo
     * @return
     */
    @Override
    public PageInfo findByPage(PageInfo<UserRole> pageInfo) {
        log.info("分页查询userRole -> pageInfo={}", JSON.toJSONString(pageInfo));
        UserRole userRole = pageInfo.getData();
        List<UserRole> userRoleList = userRoleMapper.findByPage(userRole, pageInfo);
        int count = count(userRole);

        PageInfo<List<UserRole>> pageReturn = new PageInfo<List<UserRole>>();
        pageReturn.setCount(count);
        pageReturn.setPageIndex(pageInfo.getPageIndex());
        pageReturn.setPageSize(pageInfo.getPageSize());
        pageReturn.setData(userRoleList);

        return pageReturn;
    }

    /**
     * 根据条件统计信息
     *
     * @param record
     * @return
     */
    @Override
    public int count(UserRole record) {
        log.info("根据条件计数 -> userRole={}", JSON.toJSONString(record));
        return userRoleMapper.count(record);
    }

}