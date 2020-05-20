package aoptest.currentproxy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CurrentPorxyAspectJ {

	@Pointcut("execution(public void *.method1()) ")
	public void pointcutMethod1(){

	}
	@Pointcut("execution(public void *.method2()) ")
	public void pointcutMethod2(){

	}

	@Around("pointcutMethod1()")
	public Object addMethod1(ProceedingJoinPoint joinPoint) throws Throwable{
		System.out.println("--------add-----addMethod1");
		return joinPoint.proceed();
	}
	@Around("pointcutMethod2()")
	public Object addMethod2(ProceedingJoinPoint joinPoint) throws Throwable{
		System.out.println("--------add-----addMethod2");
		return joinPoint.proceed();
	}
}
