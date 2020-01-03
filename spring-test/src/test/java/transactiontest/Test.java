package transactiontest;

import aoptest.AopTestBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) throws Exception{
		ApplicationContext ac = new ClassPathXmlApplicationContext("dataSource.xml");
		UserService bean = (UserService)ac.getBean("userService");
		System.out.println(bean);
		User user = new User();
		user.setName("yancy");
		user.setAge(29);
		user.setSex("男");
		bean.save(user);
	}
}
