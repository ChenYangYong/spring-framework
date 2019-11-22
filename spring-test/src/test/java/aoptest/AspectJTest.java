package aoptest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class AspectJTest {
	@Pointcut("execution(* *.test(..))")
	public void test(){

	}
	@Before("test()")
	public void before(){
		System.out.println("before------");
	}
	@After("test()")
	public void after(){
		System.out.println("after------");
	}
//	@AfterReturning("test()")
//	public void afterReturning(){
//		System.out.println("afterReturning------");
//	}
//	@AfterThrowing("test()")
//	public void afterThrowing(){
//		System.out.println("afterThrowing------");
//	}
	@Around("test()")
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
