package com.yangy.manage.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yangy.manage.entity.User;
import com.yangy.manage.mapper.UserMapper;
import com.yangy.manage.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author yang yang
 * @since 2018-09-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
