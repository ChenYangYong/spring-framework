package yancy;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class XmlBeanFactoryTest {
	public static void main(String[] args) {
		BeanFactory bf= new XmlBeanFactory(new ClassPathResource("beanFactoryTest.xml"));
		BeanTest bean = (BeanTest)bf.getBean("test");
		System.out.println(bean);
		Car car = (Car)bf.getBean("car");
		System.out.println(car);
		Object obj = bf.getBean("&car");
		System.out.println(obj);

	}
}
