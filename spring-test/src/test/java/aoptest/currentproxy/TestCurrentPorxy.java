package aoptest.currentproxy;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

public class TestCurrentPorxy {
	public static void main(String[] args) {
		AspectJProxyFactory proxyFactory = new AspectJProxyFactory();
		proxyFactory.setProxyTargetClass(true);
		proxyFactory.setTarget(new TargetObjectTest());
		proxyFactory.addAspect(CurrentPorxyAspectJ.class);
		//要使AopContext.currentProxy()生效，必须设置ExposeProxy为true
		proxyFactory.setExposeProxy(true);
		Object o = proxyFactory.getProxy();
		((TargetObjectTest)o).method1();
		System.out.println("-----------------------------------");
//		((TargetObjectTest)o).method2();
	}
}
