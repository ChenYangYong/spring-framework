package yancy;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.lang.reflect.Field;

public class XmlBeanFactoryTest {
	public static void main(String[] args) throws Exception{
//		MyBean myBean = new MyBean();
//		Field privateVar = myBean.getClass().getDeclaredField("message");
//		privateVar.setAccessible(true);
//		privateVar.set(myBean, "aaaaa");
//		System.out.println(myBean.getMessage());


		BeanFactory bf= new XmlBeanFactory(new ClassPathResource("beanFactoryTest.xml"));
//		ClassPathXmlApplicationContext bf = new ClassPathXmlApplicationContext("beanFactoryTest.xml");
//		BeanInjection1 injection1 = (BeanInjection1)bf.getBean("beanInjection1");
//		System.out.println(injection1);
		BeanTest bean = (BeanTest)bf.getBean("test");
		System.out.println(bean);
//		System.out.println(bean.getTest());
//		System.out.println(bean.getBeanInjection1());
//		System.out.println("&test======="+bf.getBean("&test"));
//		BeanTest bean1 = (BeanTest)bf.getBean("test1");
//		System.out.println(bean1);
//		String[] aliases = bf.getAliases("test1");
//		System.out.println(Arrays.asList(aliases));
//		Car car = (Car)bf.getBean("car");
//		System.out.println(car);
//		Object obj = bf.getBean("&car");
//		System.out.println(obj);

	}
}
