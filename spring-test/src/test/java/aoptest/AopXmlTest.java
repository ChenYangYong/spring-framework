package aoptest;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;

import java.util.Arrays;
import java.util.List;

public class AopXmlTest {
	//前置通知日志日志
	public void beforedMethod(JoinPoint joinPoint){
		String methodName=joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("AopXmlTest.beforedMethod: The method "+ methodName +" begins: "+args);
	}
	//返回通知日志
	public void afterReturningMethod(JoinPoint joinPoint, Object result){
		String methodName=joinPoint.getSignature().getName();
		System.out.println("AopXmlTest.afterReturningMethod: The method "+methodName+" ends with "+result);
	}
	// 异常通知日志
	public void afterThrowingMethod(JoinPoint joinPoint,Exception e){
		String methodName=joinPoint.getSignature().getName();
		System.out.println("AopXmlTest.afterThrowingMethod: The method "+methodName+" occurs excetion "+e);
	}
}
