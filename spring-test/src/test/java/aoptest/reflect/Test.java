package aoptest.reflect;

import aoptest.reflect.impl.Teacher;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Test {

	public static void main(String[] args) {
		saveProxyFile("D:\\workspace\\spring-framework\\spring-test\\out\\test\\classes\\aoptest\\reflect\\");
		//要代理的真实对象
		People people = new Teacher();
		//代理对象的调用处理程序，我们将要代理的真实对象传入代理对象的调用处理的构造函数中，最终代理对象的调用处理程序会调用真实对象的方法
		InvocationHandler handler = new WorkHandler(people);
		/**
		 * 通过Proxy类的newProxyInstance方法创建代理对象，我们来看下方法中的参数
		 * 第一个参数：people.getClass().getClassLoader()，使用handler对象的classloader对象来加载我们的代理对象
		 * 第二个参数：people.getClass().getInterfaces()，这里为代理类提供的接口是真实对象实现的接口，这样代理对象就能像真实对象一样调用接口中的所有方法
		 * 第三个参数：handler，我们将代理对象关联到上面的InvocationHandler对象上
		 */
		People proxy = (People) Proxy.newProxyInstance(handler.getClass().getClassLoader(), people.getClass().getInterfaces(), handler);
		//System.out.println(proxy.toString());
		System.out.println(proxy.work());
	}
	/**
	 * 保存 JDK 动态代理生产的类
	 * @param filePath 保存路径，默认在项目路径下生成 $Proxy0.class 文件
	 */
	private static void saveProxyFile(String... filePath) {
		if (filePath.length == 0) {
			System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		} else {
			FileOutputStream out = null;
			try {
				byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", Teacher.class.getInterfaces());
				out = new FileOutputStream(filePath[0] + "$Proxy0.class");
				out.write(classFile);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (out != null) {
						out.flush();
						out.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
