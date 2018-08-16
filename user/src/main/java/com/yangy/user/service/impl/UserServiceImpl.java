package com.yangy.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yangy.common.entity.User;
import com.yangy.common.model.PageInfo;
import com.yangy.user.dao.UserMapper;
import com.yangy.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yang yang
 * @email java_yangy@126.com
 * @create 2018/8/1
 * @since 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    /**
     * 添加对象
     *
     * @param user
     * @return
     */
    @Override
    public User save(User user) {
        log.info("创建用户 user->{}", JSON.toJSONString(user));
        //TODO 在此处对参数做判空处理
        user.setCreateTime(System.currentTimeMillis());
        user.setUpdateTime(System.currentTimeMillis());
        user.insertOrUpdate();
        return user;
    }

    /**
     * 修改对象
     *
     * @param user
     * @return
     */
    @Override
    public Integer update(User user) {
        log.info("修改用户 user->{}", JSON.toJSONString(user));
        user.update(new EntityWrapper<User>().eq("id", "1"));
        return null;
    }

    /**
     * 根据主键删除对象
     *
     * @param recordId
     * @return
     */
    @Override
    public Integer del(String recordId) {
        return null;
    }

    /**
     * 根据主键集合删除对象集合
     *
     * @param recordIdList
     * @return
     */
    @Override
    public Integer delByIdList(List<String> recordIdList) {
        return null;
    }

    /**
     * 根据id查询对象信息
     *
     * @param recordId
     * @return
     */
    @Override
    public User findById(String recordId) {
        return null;
    }

    /**
     * 根据id集合查询对象信息集合
     *
     * @param recordIdList
     * @return
     */
    @Override
    public List<User> findListByIdList(List<String> recordIdList) {
        return null;
    }

    /**
     * 分页查询
     *
     * @param pageInfo
     * @return
     */
    @Override
    public List<User> findByPage(PageInfo<User> pageInfo) {
        return null;
    }
}