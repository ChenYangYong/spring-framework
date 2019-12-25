package aoptest.reflect.impl;

import aoptest.reflect.People;

public class Teacher implements People {

	@Override
	public String work() {
		System.out.println("老师教书育人...");
		return "教书";
	}

}