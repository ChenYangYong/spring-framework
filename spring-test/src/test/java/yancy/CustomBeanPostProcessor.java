package yancy;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

import java.beans.PropertyDescriptor;

public class CustomBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if(bean instanceof BeanTest){
			// 如果当前的bean是Student,则打印日志
			System.out.println("CustomBeanPostProcessor.postProcessBeforeInitialization bean : " + beanName);
		}
		return bean;
	}
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(bean instanceof BeanTest){
			System.out.println("CustomBeanPostProcessor.postProcessAfterInitialization bean : " + beanName);
		}
		return bean;
	}

	@Override
	public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
		if(bean instanceof BeanTest){
			System.out.println("CustomBeanPostProcessor.postProcessAfterInitialization bean : " + beanName);
		}
		return null;
	}

	@Override
	public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
		if(bean instanceof BeanTest){
			System.out.println("CustomBeanPostProcessor.postProcessAfterInitialization bean : " + beanName);
		}
		return null;
	}
}

