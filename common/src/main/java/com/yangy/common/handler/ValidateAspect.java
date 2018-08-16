package com.yangy.common.handler;

import com.yangy.common.annotation.Validation;
import com.yangy.common.utils.ValidateUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


/**
 * 全局日志处理
 *
 * @author yang yang
 * @email java_yangy@126.com
 * @create 2018/8/1
 * @since 1.0.0
 */
@Aspect
@Component
public class ValidateAspect {

    @Pointcut("@annotation(com.yangy.common.annotation.Validation)")
    public void valAspect() {
    }

    @Before("valAspect()")
    public void valBefore(JoinPoint joinPoint) {
        //参数
        Object[] args = joinPoint.getArgs();
        //方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //方法
        Method method = signature.getMethod();
        //方法注解
        Annotation[] methodAnnotations = method.getAnnotations();
        //参数注解
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        //参数类型
        Class<?>[] parameterTypes = method.getParameterTypes();
        //参数判空
        if (null != methodAnnotations && null != parameterAnnotations) {
            for (Annotation annotation : methodAnnotations) {
                //方法上的注解为 Validation 且声明需要验证时  进行参数验证
                if (annotation instanceof Validation && ((Validation) annotation).isValidate()) {
                    ValidateUtil.validatorAnno(args, parameterAnnotations, parameterTypes);
                }
            }
        }
    }

}