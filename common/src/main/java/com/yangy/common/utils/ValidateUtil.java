package com.yangy.common.utils;

import com.yangy.common.annotation.Validation;
import com.yangy.common.enums.ResultCode;
import com.yangy.common.exception.MyException;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;

/**
 * 参数校验工具类
 *
 * @author yang yang
 * @email java_yangy@126.com
 * @create 2018/8/8
 * @since 1.0.0
 */
public class ValidateUtil {

    public static void validatorAnno(Object[] args, Annotation[][] parameterAnnotations, Class<?>[] parameterTypes) {
        for (int oneLoc = 0; oneLoc < parameterAnnotations.length; oneLoc++) {
            for (int twoLoc = 0; twoLoc < parameterAnnotations[oneLoc].length; twoLoc++) {
                Annotation anno = parameterAnnotations[oneLoc][twoLoc];
                if (anno instanceof Validation) {
                    if (((Validation) anno).notNull()) {
                        Class<?> type = parameterTypes[oneLoc];
                        Object value = args[oneLoc];
                        if (type == String.class) {
                            if (StringUtils.isBlank(((String) value))) {
                                throw new MyException(ResultCode.PARAM_ERROR);
                            }
                        } else if (null == value) {
                            throw new MyException(ResultCode.PARAM_ERROR);
                        }
                    }
                }
            }
        }
    }

}