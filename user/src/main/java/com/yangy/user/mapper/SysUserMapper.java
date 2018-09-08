package com.yangy.user.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yangy.user.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author yang yang
 * @since 2018-08-30
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}
