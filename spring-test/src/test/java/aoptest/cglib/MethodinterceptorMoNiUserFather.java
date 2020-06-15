package aoptest.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MethodinterceptorMoNiUserFather implements MethodInterceptor {
	@Override
	public Object intercept(Object obj, Method method, Object[] args,
							MethodProxy proxy) throws Throwable {
		System.err.println("Beforeinvoke " + method);
		//此方式第一个入参obj可以是父类及子类的任意实例，因为调用的父类的fast类会将该入参强制转换成父类
		Object result = proxy.invoke(new Target(), args);
		//此方式能保证互相调用，走的是父类的方法，调用的fast类也是子类的fast类，
		// 第一个入参obj只能是子类的实例，因为该fast类会将该入参强制转换成子类
//		Object result = proxy.invokeSuper(obj, args);
		System.err.println("Afterinvoke " + method);
		return result;

	}
}
