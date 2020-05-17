package aoptest.targetsource;

import aoptest.AopTestBean;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TargetSourceTestMain {
	public static void main(String[] args) {
		ITask iTask1  = new ITask() {
			@Override
			public void execute() {
				System.out.println("iTask1");
			}
		};
		ITask iTask2  = new ITask() {
			@Override
			public void execute() {
				System.out.println("iTask2");
			}
		};
		MyTargetSource targetSource = new MyTargetSource(iTask1,iTask2);
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTargetSource(targetSource);
		Object proxy1 = proxyFactory.getProxy();
		((ITask)proxy1).execute();
		((ITask)proxy1).execute();
		((ITask)proxy1).execute();
		((ITask)proxy1).execute();
	}
}
