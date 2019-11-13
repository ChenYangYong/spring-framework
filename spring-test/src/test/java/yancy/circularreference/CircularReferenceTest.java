package yancy.circularreference;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class CircularReferenceTest {
	@Test
	public void testCircleByConstructor() throws Throwable {
		try {
			BeanFactory bf=new XmlBeanFactory(new ClassPathResource("circularreferenceTest.xml"));
			System.out.println(bf.getBean("testA"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
