package yancy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class BeanTest {
	@Autowired
	private BeanInjection1 beanInjection1;

//	@Value("${test}")
	private String test;

//	@Value("${date}")
	private Date date;

	@Value("${initValue}")
	private String initValue;

	private String noSetStr;

	private String hasSetStr;

	public BeanTest() {
		System.out.println("BeanTest无参创建");
	}
	public BeanTest(int param) {
		System.out.println("BeanTest单个参数创建参数="+param);
	}
	public BeanTest(String param) {
		System.out.println("BeanTest单个参数创建参数="+param);
	}
	public BeanTest(int param1,int param2) {
		System.out.println("BeanTest两个参数创建参数param1="+param1+",param2="+param2);
	}
	public BeanTest(int param1,String param2) {
		System.out.println("BeanTest两个参数创建参数param1="+param1+",param2="+param2);
	}

	public void init(){
		System.out.println("开始进行初始化");
		this.initValue = "初始化";
	}

	public BeanInjection1 getBeanInjection1() {
		return beanInjection1;
	}
////
//	public void setBeanInjection1(BeanInjection1 beanInjection1) {
//		this.beanInjection1 = beanInjection1;
//	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setHasSetStr(String hasSetStr) {
		this.hasSetStr = hasSetStr;
	}

	@Override
	public String toString() {
		return "BeanTest{" +
				"beanInjection1=" + beanInjection1 +
				", test='" + test + '\'' +
				", date=" + date +
				", initValue='" + initValue + '\'' +
				", noSetStr='" + noSetStr + '\'' +
				", hasSetStr='" + hasSetStr + '\'' +
				'}';
	}
}
