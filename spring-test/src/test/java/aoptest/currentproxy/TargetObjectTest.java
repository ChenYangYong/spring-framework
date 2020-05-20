package aoptest.currentproxy;

import org.springframework.aop.framework.AopContext;

public class TargetObjectTest {
	public void method1(){
		System.out.println("----method1----");
//		method2();
//		((TargetObjectTest)AopContext.currentProxy()).method2();
		getThis().method2();
	}
	public void method2(){
		System.out.println("----method2----");
	}
	private TargetObjectTest getThis(){
		return (TargetObjectTest)AopContext.currentProxy();
	}
}
