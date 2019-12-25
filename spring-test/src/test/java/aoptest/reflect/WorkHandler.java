package aoptest.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class WorkHandler implements InvocationHandler {

	//代理类中的真实对象
	private Object obj;

	public WorkHandler() {
		// TODO Auto-generated constructor stub
	}
	//构造函数，给我们的真实对象赋值
	public WorkHandler(Object obj) {
		this.obj = obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//在真实的对象执行之前我们可以添加自己的操作
		System.out.println("before invoke。。。");
		Object invoke = method.invoke(obj, args);
		//在真实的对象执行之后我们可以添加自己的操作
		System.out.println("after invoke。。。");
		return invoke;
	}
	public Object getProxy(Object object) {
		Class clazz = object.getClass();
		ClassLoader classLoader = clazz.getClassLoader();
		Class[] interfaces = clazz.getInterfaces();
		return Proxy.newProxyInstance(classLoader, interfaces, this);
	}

}

