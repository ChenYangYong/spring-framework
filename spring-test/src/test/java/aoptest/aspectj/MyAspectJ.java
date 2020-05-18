package aoptest.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MyAspectJ {

	@Pointcut("execution(public void *.method1()) || execution(public void *.method2()) ")
	public void pointcutName(){

	}

	@Around("pointcutName()")
	public Object add(ProceedingJoinPoint joinPoint) throws Throwable{
		System.out.println("--------add-----start");
		return joinPoint.proceed();
	}
}
