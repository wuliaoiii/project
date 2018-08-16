package com.yangy.manage.service.impl;

import com.alibaba.fastjson.JSON;
import com.yangy.common.entity.Role;
import com.yangy.common.enums.ResultCode;
import com.yangy.common.exception.MyException;
import com.yangy.common.model.PageInfo;
import com.yangy.manage.dao.RoleMapper;
import com.yangy.manage.service.RoleService;
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
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    /**
     * 添加记录
     *
     * @param record
     */
    @Override
    @Transactional
    public long save(Role record) {
        log.info("添加role -> role={}", JSON.toJSONString(record));
        try {
            roleMapper.save(record);
        } catch (MyException e) {
            throw new MyException(ResultCode.SAVE_FAIL);
        }
        return record.getRuleId();
    }

    /**
     * 批量添加记录
     *
     * @param roleList
     * @return
     */
    @Override
    @Transactional
    public int saveList(List<Role> roleList) {
        log.info("批量添加 role -> roleList={}", JSON.toJSONString(roleList));
        int saveResult = 0;
        try {
            saveResult = roleMapper.saveList(roleList);
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
    public long update(Role record) {
        log.info("修改role-> role={}", JSON.toJSONString(record));
        if (null == record.getRuleId()) {
            throw new MyException(ResultCode.PARAM_ERROR);
        }
        try {
            roleMapper.update(record);
        } catch (MyException e) {
            throw new MyException(ResultCode.UPDATE_FAIL);
        }
        return record.getRuleId();
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
        log.info("删除role -> id={}", id);
        if (null == id) {
            throw new MyException(ResultCode.PARAM_ERROR);
        }
        int delResult = 0;
        try {
            delResult = roleMapper.delById(id);
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
    public int del(Role record) {
        log.info("根据条件删除role -> role={}", JSON.toJSONString(record));
        if (null == record) {
            throw new MyException(ResultCode.PARAM_ERROR);
        }
        int delResult = 0;
        try {
            delResult = roleMapper.delByRole(record);
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
        log.info("批量删除role -> idList={}", JSON.toJSONString(idList));
        if (CollectionUtils.isEmpty(idList)) {
            throw new MyException(ResultCode.PARAM_ERROR);
        }
        int delResult = 0;
        try {
            delResult = roleMapper.delByIdList(idList);
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
    public Role findById(Long id) {
        log.info("根据主键查询role -> id={}", id);
        return null == id ? null : roleMapper.findById(id);
    }

    /**
     * 查询满足条件的第一条记录
     *
     * @param record
     * @return
     */
    @Override
    @Transactional
    public Role findFirst(Role record) {
        log.info("根据条件查询role的第一条记录 -> role={}", JSON.toJSONString(record));
        return roleMapper.findFirst(record);
    }

    /**
     * 根据 id 集合查询信息
     *
     * @param idList
     * @return
     */
    @Override
    public List<Role> findByIdList(List<Long> idList) {
        log.info("根据主键集合查询role -> idList={}", JSON.toJSONString(idList));
        return roleMapper.findByIdList(idList);
    }

    /**
     * 根据条件查询信息
     *
     * @param record
     * @return
     */
    @Override
    public List<Role> findByParam(Role record) {
        log.info("根据条件查询role -> role={}", JSON.toJSONString(record));
        return roleMapper.findByParam(record);
    }

    /**
     * 根据条件分页查询信息
     *
     * @param pageInfo
     * @return
     */
    @Override
    public PageInfo findByPage(PageInfo<Role> pageInfo) {
        log.info("分页查询role -> pageInfo={}", JSON.toJSONString(pageInfo));
        Role role = pageInfo.getData();
        List<Role> roleList = roleMapper.findByPage(role, pageInfo);
        int count = count(role);

        PageInfo<List<Role>> pageReturn = new PageInfo<List<Role>>();
        pageReturn.setCount(count);
        pageReturn.setPageIndex(pageInfo.getPageIndex());
        pageReturn.setPageSize(pageInfo.getPageSize());
        pageReturn.setData(roleList);

        return pageReturn;
    }

    /**
     * 根据条件统计信息
     *
     * @param record
     * @return
     */
    @Override
    public int count(Role record) {
        log.info("根据条件计数 -> role={}", JSON.toJSONString(record));
        return roleMapper.count(record);
    }

}