package com.yangy.manage.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yangy.manage.entity.Menu;
import com.yangy.manage.mapper.MenuMapper;
import com.yangy.manage.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author yang yang
 * @since 2018-09-04
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
