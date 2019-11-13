package yancy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class BeanTest {
	@Autowired
	private BeanInjection1 beanInjection1;

	@Value("${test}")
	private String test;

	public BeanTest() {
		System.out.println("BeanTest无参初始化");
	}
	public BeanTest(int param) {
		System.out.println("BeanTest单个参数初始化参数="+param);
	}
	public BeanTest(String param) {
		System.out.println("BeanTest单个参数初始化参数="+param);
	}
	public BeanTest(int param1,int param2) {
		System.out.println("BeanTest两个参数初始化参数param1="+param1+",param2="+param2);
	}
	public BeanTest(int param1,String param2) {
		System.out.println("BeanTest两个参数初始化参数param1="+param1+",param2="+param2);
	}

	public BeanInjection1 getBeanInjection1() {
		return beanInjection1;
	}
//
//	public void setBeanInjection1(BeanInjection1 beanInjection1) {
//		this.beanInjection1 = beanInjection1;
//	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
}
