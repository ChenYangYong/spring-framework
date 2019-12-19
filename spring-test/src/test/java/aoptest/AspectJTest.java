package aoptest;

import aoptest.impl.UserServiceImpl;
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
		System.out.println("before------"+this);
	}
	@After("testAop()")
	public void after(JoinPoint joinPoint){
		System.out.println("after------"+this);
	}
	@AfterReturning("testAop()")
	public void afterReturning(JoinPoint joinPoint){
		System.out.println("afterReturning------"+this);
	}
	@AfterThrowing("testAop()")
	public void afterThrowing(JoinPoint joinPoint){
		System.out.println("afterThrowing------"+this);
	}
//	@Around("testAop()")
	public Object arountTest(ProceedingJoinPoint p ) {
		System.out.println("arount start"+this);
		Object o=null ;
		try {
			o = p.proceed();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		System.out.println("arount end"+this);
		return o;
	}

	/**
	 * value：相当于XML中的types-matching，待引入增强作用的类的表达式， 支持通配符
	 * 	defaultImpl：引入增强的具体实现
	 */
	@DeclareParents(value = "aoptest.AopTestBean", defaultImpl = UserServiceImpl.class)
	public UserService userService;
}
