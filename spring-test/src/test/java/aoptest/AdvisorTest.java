package aoptest;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class AdvisorTest implements MethodBeforeAdvice, AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("AdvisorTest----afterReturning----"+method.getName());
	}

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("AdvisorTest----before----"+method.getName());
	}
}
