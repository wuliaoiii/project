package com.yangy.common.annotation;

import java.lang.annotation.Annotation;

/**
 * 注解帮助类
 *
 * @author michael.bai
 * @date 2016年12月20日
 */
public class AnnotationHelper {

    /**
     * 获取参数的描述
     *
     * @param method
     * @param objs
     * @return
     */
//    public static List<Param> getParams(Method method, Object[] objs) {
//
//        Annotation[][] annos = method.getParameterAnnotations();
//        Class<?>[] paramTypes = method.getParameterTypes();
//        List<Param> params = new ArrayList<Param>();
//        for (int i = 0; i < annos.length; i++) {
//            for (int j = 0; j < annos[i].length; j++) {
//                //如果出现指定的注解类型
//                if (annos[i][j] instanceof Validation){
//                    Param param = new Param(paramTypes[i].getSimpleName(),
//                            paramTypes[i].getName(),//名称
//                            paramTypes[i],//参数类型
//                            objs[i],//参数值
//                            annos[i][j]);//筛选出的注解
//                    params.add(param);
//                }
//            }
//        }
//        return params;
//    }

    /**
     * 校验是否存在参数注解
     *
     * @param annotations
     * @return
     */
    public static boolean validateParameterAnnotation(Annotation[][] annotations) {
        for (Annotation[] annotation : annotations) {
            for (Annotation an : annotation) {
                if (an instanceof Validation) {
                    return true;
                }
            }
        }
        return false;
    }
}
