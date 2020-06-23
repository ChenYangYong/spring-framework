package aoptest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopMain {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("aopTest.xml");

		AopTestBean bean = (AopTestBean)ac.getBean("proxyObjectTest");
		System.out.println(bean);
		bean.test("aaaaaaaaa");
		System.out.println("--------------------------");
//		UserService service = (UserService)bean;
//		service.a();


//		UserService bean = (UserService)ac.getBean("userService");
//		System.out.println(bean);
//		bean.test();
	}
}
