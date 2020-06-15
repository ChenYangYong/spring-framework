package aoptest.aspectj;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.cglib.core.DebuggingClassWriter;

public class TestAspect {
	public static void main(String[] args) {
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\111");
		AspectJProxyFactory proxyFactory = new AspectJProxyFactory();
		proxyFactory.setProxyTargetClass(true);
		proxyFactory.setTarget(new Foo());
		proxyFactory.addAspect(MyAspectJ.class);
		Object o = proxyFactory.getProxy();
		((Foo)o).method1();
//		((Foo)o).method2();
	}
}
