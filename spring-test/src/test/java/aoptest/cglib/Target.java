package aoptest.cglib;

public class Target {

	private String value;

	public void test() {
		System.out.println("Target test ()");
		this.test2();
	}

	public void test2() {
		System.out.println("Target test2 ()");
	}
	public String out(String input){
		return "hello world";
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
