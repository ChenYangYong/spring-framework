package aoptest.cglib;

import org.springframework.beans.factory.support.CglibSubclassingInstantiationStrategy;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class EnhancerDemo {
	public static void main (String [] args) {
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\111");
		Enhancer enhancer = new Enhancer ();
		enhancer.setSuperclass(EnhancerDemo.class);
		enhancer.setCallback(new Methodinterceptorimpl());

		EnhancerDemo demo= (EnhancerDemo)enhancer.create() ;
		demo.test();
//		System.out .println (demo);
//		System.out.println(demo instanceof Factory);
	}
	public void test(){
		System.out.println ("EnhancerDemo test ()"+this) ;
		this.test2();
	}

	public void test2(){
		System.out.println ("EnhancerDemo test2 ()") ;
	}

	private static class Methodinterceptorimpl implements MethodInterceptor {
		@Override
		public Object intercept(Object obj , Method method , Object[] args ,
								MethodProxy proxy) throws Throwable {
			System.err.println ("Beforeinvoke " + method) ;
			//对一个类产生代理类时，对每一个方法会产生一个同名的方法以及一个“CGLIB$被代理方法$0”的方法
			//该方法内部直接调用的super.被代理方法名

			//此处调用的是代理类的另一个方法（即“CGLIB$被代理方法$0”），方法内部直接调用的super.被代理方法名
			Object result= proxy.invokeSuper(obj, args);

			//以下方式调用会进入死循环
			//原因:proxy.invoke会调用代理类的代理方法（如代理类重写的test方法），而代理类的代理方法
			//又会调用拦截器的intercept方法，如果intercept再调用proxy.invoke就会死循环
//			Object result= proxy.invoke(obj,args);


			System.err.println ("Afterinvoke"+ method);
			return result ;
		}
	}

}
