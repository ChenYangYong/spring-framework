package yancy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClassPathXmlApplicationContextTest {
	public static void main(String[] args) {
		ApplicationContext bf = new ClassPathXmlApplicationContext("beanFactoryTest.xml");
//		BeanInjection1 injection1 = (BeanInjection1)bf.getBean("beanInjection1");
//		System.out.println(injection1);
		BeanTest bean = (BeanTest)bf.getBean("test");
		System.out.println(bean);
		System.out.println(bean.getTest());
		System.out.println(bean.getBeanInjection1());
		System.out.println(bean.getDate());
	}
}
