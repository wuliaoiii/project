package com.yangy.user.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yangy.user.entity.SysUser;
import com.yangy.user.mapper.SysUserMapper;
import com.yangy.user.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author yang yang
 * @since 2018-08-30
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

}
