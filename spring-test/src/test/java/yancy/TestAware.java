package yancy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class TestAware implements BeanFactoryAware {
	private BeanFactory beanFactory;
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}
	public void testAware(){
		BeanTest bean = (BeanTest)beanFactory.getBean("test");
		System.out.println(bean);
	}
}
