package com.yangy.common.handler;

import com.yangy.common.exception.MyException;
import com.yangy.common.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理handler
 *
 * @author yang yang
 * @email java_yangy@126.com
 * @create 2018/8/1
 * @since 1.0.0
 */
@Component
@RestControllerAdvice
public class BaseExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(BaseExceptionHandler.class);

    @ExceptionHandler(MyException.class)
    public Result handleMyException(final MyException exception, HttpServletResponse response) {
        response.setStatus(200);
        return new Result<String>().error(exception.getStatus(), exception.getMsg());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result handleException(final Exception exception, HttpServletResponse response) {
        response.setStatus(200);
        return null;
    }

}