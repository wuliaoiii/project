package com.yangy.manage.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yangy.manage.entity.Log;
import com.yangy.manage.mapper.LogMapper;
import com.yangy.manage.service.LogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志 服务实现类
 * </p>
 *
 * @author yang yang
 * @since 2018-09-04
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
