package yancy;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

public class XmlBeanFactoryTest {
	public static void main(String[] args) {
		BeanFactory bf= new XmlBeanFactory(new ClassPathResource("beanFactoryTest.xml"));
		BeanTest bean = (BeanTest)bf.getBean("test");
		System.out.println(bean);
//		System.out.println("&test======="+bf.getBean("&test"));
//		BeanTest bean1 = (BeanTest)bf.getBean("test1");
//		System.out.println(bean1);
//		String[] aliases = bf.getAliases("test1");
//		System.out.println(Arrays.asList(aliases));
		Car car = (Car)bf.getBean("car");
		System.out.println(car);
//		Object obj = bf.getBean("&car");
//		System.out.println(obj);

	}
}
