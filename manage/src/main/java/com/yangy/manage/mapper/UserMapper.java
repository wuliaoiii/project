package com.yangy.manage.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yangy.manage.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author yang yang
 * @since 2018-09-04
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
