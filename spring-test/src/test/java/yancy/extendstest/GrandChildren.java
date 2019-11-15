package yancy.extendstest;

public class GrandChildren extends Child {
	@Override
	protected void childMethod(){
		System.out.println("运行  GrandChildren.childMethod");
	}
}
