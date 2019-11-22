package aoptest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class AspectJTest {
	@Pointcut("execution(* *.test(..))")
	public void testAop(){

	}
	@Before("testAop()")
	public void before(JoinPoint joinPoint){
		System.out.println("before------");
	}
	@After("testAop()")
	public void after(JoinPoint joinPoint){
		System.out.println("after------");
	}
	@AfterReturning("testAop()")
	public void afterReturning(JoinPoint joinPoint){
		System.out.println("afterReturning------");
	}
	@AfterThrowing("testAop()")
	public void afterThrowing(JoinPoint joinPoint){
		System.out.println("afterThrowing------");
	}
	@Around("testAop()")
	public Object arountTest(ProceedingJoinPoint p ) {
		System.out.println("arount start");
		Object o=null ;
		try {
			o = p.proceed();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		System.out.println("arount end");
		return o;
	}
}
