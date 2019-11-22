package aoptest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopMain {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("aopTest.xml");
		AopTestBean bean = (AopTestBean)ac.getBean("aoptest");
		System.out.println(bean);
		bean.test();
	}
}
