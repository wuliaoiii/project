package com.yangy.manage.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yangy.manage.entity.Log;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 操作日志 Mapper 接口
 * </p>
 *
 * @author yang yang
 * @since 2018-09-04
 */
@Mapper
public interface LogMapper extends BaseMapper<Log> {

}
