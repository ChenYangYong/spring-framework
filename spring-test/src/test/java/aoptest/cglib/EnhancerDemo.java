package aoptest.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class EnhancerDemo {
	public static void main (String [] args) {
		Enhancer enhancer = new Enhancer ();
		enhancer.setSuperclass(EnhancerDemo.class);
		enhancer.setCallback(new Methodinterceptorimpl());
		EnhancerDemo demo= (EnhancerDemo)enhancer.create() ;
		demo.test();
		System.out .println (demo);
	}
	public void test(){
		System.out.println ("EnhancerDemo test ()" ) ;
	}

	private static class Methodinterceptorimpl implements MethodInterceptor {
		@Override
		public Object intercept(Object obj , Method method , Object[] args ,
								MethodProxy proxy) throws Throwable {
			System.err.println ("Beforeinvoke " + method ) ;
			Object result= proxy.invokeSuper(obj, args);
			System.err.println ("Afterinvoke"+ method);
			return result ;
		}
	}

}
