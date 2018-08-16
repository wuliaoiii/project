package com.yangy.manage.service.impl;

import com.alibaba.fastjson.JSON;
import com.yangy.common.entity.User;
import com.yangy.common.enums.ResultCode;
import com.yangy.common.exception.MyException;
import com.yangy.common.model.PageInfo;
import com.yangy.manage.dao.UserMapper;
import com.yangy.manage.service.UserService;
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
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 添加记录
     *
     * @param record
     */
    @Override
    @Transactional
    public long save(User record) {
        log.info("添加user -> user={}", JSON.toJSONString(record));
        try {
            userMapper.save(record);
        } catch (MyException e) {
            throw new MyException(ResultCode.SAVE_FAIL);
        }
        return record.getUserId();
    }

    /**
     * 批量添加记录
     *
     * @param userList
     * @return
     */
    @Override
    @Transactional
    public int saveList(List<User> userList) {
        log.info("批量添加 user -> userList={}", JSON.toJSONString(userList));
        int saveResult = 0;
        try {
            saveResult = userMapper.saveList(userList);
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
    public long update(User record) {
        log.info("修改user-> user={}", JSON.toJSONString(record));
        if (null == record.getUserId()) {
            throw new MyException(ResultCode.PARAM_ERROR);
        }
        try {
            userMapper.update(record);
        } catch (MyException e) {
            throw new MyException(ResultCode.UPDATE_FAIL);
        }
        return record.getUserId();
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
        log.info("删除user -> id={}", id);
        if (null == id) {
            throw new MyException(ResultCode.PARAM_ERROR);
        }
        int delResult = 0;
        try {
            delResult = userMapper.delById(id);
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
    public int del(User record) {
        log.info("根据条件删除user -> user={}", JSON.toJSONString(record));
        if (null == record) {
            throw new MyException(ResultCode.PARAM_ERROR);
        }
        int delResult = 0;
        try {
            delResult = userMapper.delByUser(record);
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
        log.info("批量删除user -> idList={}", JSON.toJSONString(idList));
        if (CollectionUtils.isEmpty(idList)) {
            throw new MyException(ResultCode.PARAM_ERROR);
        }
        int delResult = 0;
        try {
            delResult = userMapper.delByIdList(idList);
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
    public User findById(Long id) {
        log.info("根据主键查询user -> id={}", id);
        return null == id ? null : userMapper.findById(id);
    }

    /**
     * 查询满足条件的第一条记录
     *
     * @param record
     * @return
     */
    @Override
    @Transactional
    public User findFirst(User record) {
        log.info("根据条件查询user的第一条记录 -> user={}", JSON.toJSONString(record));
        return userMapper.findFirst(record);
    }

    /**
     * 根据 id 集合查询信息
     *
     * @param idList
     * @return
     */
    @Override
    public List<User> findByIdList(List<Long> idList) {
        log.info("根据主键集合查询user -> idList={}", JSON.toJSONString(idList));
        return userMapper.findByIdList(idList);
    }

    /**
     * 根据条件查询信息
     *
     * @param record
     * @return
     */
    @Override
    public List<User> findByParam(User record) {
        log.info("根据条件查询user -> user={}", JSON.toJSONString(record));
        return userMapper.findByParam(record);
    }

    /**
     * 根据条件分页查询信息
     *
     * @param pageInfo
     * @return
     */
    @Override
    public PageInfo findByPage(PageInfo<User> pageInfo) {
        log.info("分页查询user -> pageInfo={}", JSON.toJSONString(pageInfo));
        User user = pageInfo.getData();
        List<User> userList = userMapper.findByPage(user, pageInfo);
        int count = count(user);

        PageInfo<List<User>> pageReturn = new PageInfo<List<User>>();
        pageReturn.setCount(count);
        pageReturn.setPageIndex(pageInfo.getPageIndex());
        pageReturn.setPageSize(pageInfo.getPageSize());
        pageReturn.setData(userList);

        return pageReturn;
    }

    /**
     * 根据条件统计信息
     *
     * @param record
     * @return
     */
    @Override
    public int count(User record) {
        log.info("根据条件计数 -> user={}", JSON.toJSONString(record));
        return userMapper.count(record);
    }

}