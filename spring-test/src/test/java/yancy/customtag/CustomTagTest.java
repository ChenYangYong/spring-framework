package yancy.customtag;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class CustomTagTest {
	public static void main(String[] args) {
		BeanFactory bf= new XmlBeanFactory(new ClassPathResource("custom-tag-test.xml"));
		User user = (User)bf.getBean("testbean");
		System.out.println(user.getEmail()+"  "+user.getUserName());
	}
}
