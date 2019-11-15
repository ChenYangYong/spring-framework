package yancy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class TestBeanPostProcessor implements BeanPostProcessor {
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("TestBeanPostProcessor.postProcessBeforeInitialization处理bean="+bean+",beanName="+beanName);
		return null;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("TestBeanPostProcessor.postProcessAfterInitialization="+bean+",beanName="+beanName);
		return null;
	}
}
