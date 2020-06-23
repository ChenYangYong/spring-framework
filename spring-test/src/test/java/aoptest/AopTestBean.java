package aoptest;

import org.springframework.beans.factory.annotation.Value;

public class AopTestBean {
	@Value("${test}")
	private String testStr = "AopTestBean";

	public String getTestStr() {
		return testStr;
	}

	public void setTestStr(String testStr) {
		this.testStr = testStr;
	}
	public void test(String str){
		System.out.println(testStr+"---"+str);
	}

//	@Override
//	public String toString() {
//		return "AopTestBean{" +
//				"testStr='" + testStr + '\'' +
//				'}';
//	}
}
