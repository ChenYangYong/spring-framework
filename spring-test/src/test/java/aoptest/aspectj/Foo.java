package aoptest.aspectj;

public class Foo {
	public void method1(){
		System.out.println("----method1----"+this);
		//内部调用导致method2不会被代理
		this.method2();
	}
	public void method2(){
		System.out.println("----method2----"+this);
	}
}
