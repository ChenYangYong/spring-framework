package aoptest.aspectj;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

public class TestAspect {
	public static void main(String[] args) {
		AspectJProxyFactory proxyFactory = new AspectJProxyFactory();
		proxyFactory.setProxyTargetClass(true);
		proxyFactory.setTarget(new Foo());
		proxyFactory.addAspect(MyAspectJ.class);
		Object o = proxyFactory.getProxy();
		((Foo)o).method1();
		((Foo)o).method2();
	}
}
