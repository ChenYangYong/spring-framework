package aoptest;

public class AopTestBean {
	private String testStr = "AopTestBean";

	public String getTestStr() {
		return testStr;
	}

	public void setTestStr(String testStr) {
		this.testStr = testStr;
	}
	public void test(){
		System.out.println(testStr);
	}

//	@Override
//	public String toString() {
//		return "AopTestBean{" +
//				"testStr='" + testStr + '\'' +
//				'}';
//	}
}
