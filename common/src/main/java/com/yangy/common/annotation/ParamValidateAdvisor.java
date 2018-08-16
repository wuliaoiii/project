//package com.yangy.common.annotation;
//
//import com.test.framework.common.util.Check;
//import com.test.rest.api.web.validators.annos.Must;
//import com.test.rest.api.web.validators.annos.Validate;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.util.List;
//
///**
// * 参数验证
// * @author michael.bai
// * @date 2016年12月20日
// */
//public class ParamValidateAdvisor{
//	private Logger logger = LoggerFactory.getLogger(ParamValidateAdvisor.class);
//
//	/**
//	 * 校验入参
//	 * @param point
//	 * @throws Throwable
//	 */
//	public Object validate(ProceedingJoinPoint point) throws Throwable{
//		logger.info("开始拦截接口入参!");
//		Object[] objs = point.getArgs();
//		MethodSignature signature = (MethodSignature) point.getSignature();
//		Method method = signature.getMethod();
//
//		//检测
//		Annotation[][] annos = method.getParameterAnnotations();
//		boolean flag = validateParameterAnnotation(annos);
//		//虽然方法加了注解,但是参数么有注解,pass
//		if(!flag){
//			return point.proceed(objs);
//		}
//
//		//得到标注@Validate注解的参数
//		List<Param> params = AnnotationHelper.getParams(method,objs);
//		if(!Check.NuNList(params)){
//			for(Param param : params){
//				String validRes = validateDetail(param);
//				if(!Check.NuNString(validRes)){
//					logger.info("客户端上报参数错误详细信息:{}",validRes);
////					return ResponseUtil.message(ErrorCode.CLIENT_ERROR.getCode(), "客户端上报参数错误");
//				}
//			}
//		}
//		//没有错误就沿着毛主席的路线继续前进!
//		return point.proceed(objs);
//	}
//
//	/**
//	 * 具体的校验逻辑,返回警告信息
//	 * @param obj
//	 * @return
//	 * @throws IllegalAccessException
//	 * @throws IllegalArgumentException
//	 */
//	private String validateDetail(Param param) throws IllegalArgumentException, IllegalAccessException{
//		Validate val = (Validate)param.getAnno();
//		boolean isVali = val.isValidate();
//		StringBuilder sb = new StringBuilder();
//		if(isVali){
//			if(val.isForm() == true){
//				String res = validateForm(param);
//				append(sb,res);
//			}else{
//				String res = validateCommon(param);
//				append(sb,res);
//			}
//		}
//		return sb.toString();
//	}
//
//	private void append(StringBuilder sb,String res){
//		if(!Check.NuNString(res)){
//			sb.append("_");
//			sb.append(res);
//		}
//	}
//
//	/**
//	 * 验证是否有某个注解
//	 * @param annos
//	 * @param validate
//	 * @return
//	 */
//	private boolean validateParameterAnnotation(Annotation[][] annos){
//		boolean flag = false;
//		for(Annotation[] at : annos){
//			for(Annotation a : at){
//				if(a.annotationType() == Validate.class){
//					flag = true;
//				}
//			}
//		}
//		return flag;
//	}
//
//	private String  validateCommon(Param param){
//		String res = null;
//		if(Check.NuNObject(param.getValue())){
//			res = param.getName()+"的参数值为空!";
//		}
//		return res;
//	}
//
//	private String validateForm(Param param) throws IllegalArgumentException, IllegalAccessException{
//		Class<?> clazz = param.getValue().getClass();
//		Field[] fields = clazz.getDeclaredFields();
//		StringBuilder sb = new StringBuilder();
//		for(Field f : fields){
//			Annotation[] annos = f.getAnnotations();
//			if(!Check.NuNArray(annos)){
//				String paramName = param.getName()+"."+f.getName();
//				Validation must = (Validation) annos[0];
//				if(must.notNull()){
//					f.setAccessible(true);
//					Object obj = f.get(param.getValue());
//					Class<?> type = f.getType();
//					if(type.isArray()){
//						Object[] arr = (Object[])obj;
//						if(Check.NuNArray(arr)){
//							append(sb, paramName+"不能为空!");
//						}
//					}else if(type.isPrimitive()){
//						if(type == int.class){
//							int intObj = (int)obj;
//							if(intObj <= 0){
//								append(sb, paramName+"不能小于等于0!");
//							}
//						}else if(type == long.class){
//							long longObj = (long)obj;
//							if(longObj <= 0){
//								append(sb, paramName+"不能小于等于0!");
//							}
//						}
//					}else if(type == String.class){
//						if(Check.NuNString((String)obj)){
//							append(sb, paramName+"不能为空!");
//						}
//					}
//				}
//			}
//		}
//		return sb.toString();
//	}
//}
