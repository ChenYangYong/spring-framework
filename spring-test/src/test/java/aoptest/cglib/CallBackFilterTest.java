package aoptest.cglib;

import org.springframework.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

public class CallBackFilterTest implements CallbackFilter {
	/**
	 * 方法返回的值是和callback回调接口数组一一对应的数组下标
	 */
	@Override
	public int accept(Method method) {
		String name = method.getName();
		if("out".equals(name)) {
			return 0;
		}else if("test2".equals(name)){
			return 2;
		}
		return 1;
	}

}
