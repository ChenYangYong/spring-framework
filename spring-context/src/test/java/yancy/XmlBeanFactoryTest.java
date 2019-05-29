package yancy;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class XmlBeanFactoryTest {
	public static void main(String[] args) {
		BeanFactory bf= new XmlBeanFactory(new ClassPathResource("beanFactoryTest.xml"));
		System.out.println(bf.getBean("test"));
	}
}
