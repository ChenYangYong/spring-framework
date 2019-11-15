package yancy.extendstest;

public class Child implements FatherInterface {
	@Override
	public void fatherMethod() {
		System.out.println("运行Child.fatherMethod");
		childMethod();
	}
	protected void childMethod(){
		System.out.println("运行Child.childMethod");
	}
}
