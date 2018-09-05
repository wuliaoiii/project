package com.yangy.manage.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yangy.manage.entity.Role;
import com.yangy.manage.mapper.RoleMapper;
import com.yangy.manage.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author yang yang
 * @since 2018-09-04
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
