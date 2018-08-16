//package com.yangy.common.handler;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Arrays;
//
//
///**
// * 全局日志处理
// *
// * @author yang yang
// * @email java_yangy@126.com
// * @create 2018/8/1
// * @since 1.0.0
// */
//@Aspect
//@Component
//public class LogAspect {
//
//    @Pointcut("execution(public * com.yangy.*.controller.*.*(..))")
//    public void logAspect() {
//    }
//
//    @Pointcut("@annotation(com.yangy.common.annotation.Validation)")
//    public void valAspect() {
//    }
//
//    @Before("logAspect()")
//    public void deBefore(JoinPoint joinPoint) throws Throwable {
//        // 接收到请求，记录请求内容
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        // 记录下请求内容
//        System.out.println("URL : " + request.getRequestURL().toString());
//        System.out.println("HTTP_METHOD : " + request.getMethod());
//        System.out.println("IP : " + request.getRemoteAddr());
//        System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));
//
//    }
//
//    @AfterReturning(returning = "ret", pointcut = "logAspect()")
//    public void doAfterReturning(Object ret) throws Throwable {
//        // 处理完请求，返回内容
//        System.out.println("方法的返回值 : " + ret);
//    }
//
//    //后置异常通知
//    @AfterThrowing("logAspect()")
//    public void throwss(JoinPoint jp) {
//        System.out.println("方法异常时执行.....");
//    }
//
//    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
//    @After("logAspect()")
//    public void after(JoinPoint jp) {
//        System.out.println("方法最后执行.....");
//    }
//
//    //环绕通知,环绕增强，相当于MethodInterceptor
//    @Around("logAspect()")
//    public Object arround(ProceedingJoinPoint pjp) {
//        System.out.println("方法环绕start.....");
//        try {
//            Object o = pjp.proceed();
//            System.out.println("方法环绕proceed，结果是 :" + o);
//            return o;
//        } catch (Throwable e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
////    @Before("valAspect()")
////    public void valBefore(JoinPoint joinPoint) {
////        Object[] args = joinPoint.getArgs();
////        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
////        Method method = signature.getMethod();
////        Annotation[] methodAnnotations = method.getAnnotations();
////
////        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
////        for (Annotation annotation : methodAnnotations) {
////            if (annotation instanceof Validation) {
////                boolean flag = AnnotationHelper.validateParameterAnnotation(parameterAnnotations);
////                if (flag) {
////
////                }
////            }
////        }
////    }
//
//}