package aoptest.cglib;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.cglib.beans.*;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.core.KeyFactory;
import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

public class TestCglibApi {
	@Test
	public void testMethodInterceptor() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Target.class);
		enhancer.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				System.out.println("before method run..." + method);
				Object result = proxy.invokeSuper(obj, args);
				System.out.println("after method run...");
				return result;
			}
		});
		Target sample = (Target) enhancer.create();
		sample.test();
	}

	/**
	 * FixedValue用来对所有拦截的方法返回相同的值，从输出我们可以看出来，Enhancer对非final方法test()、
	 * toString()、hashCode()进行了拦截，没有对getClass进行拦截。由于hashCode()方法需要返回一个Number，
	 * 但是我们返回的是一个String，这解释了上面的程序中为什么会抛出异常。
	 */
	@Test
	public void testFixedValue(){
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Target.class);
		enhancer.setCallback(new FixedValue() {
			@Override
			public Object loadObject() throws Exception {
				return "Hello cglib";
			}
		});
		Target proxy = (Target) enhancer.create();
		System.out.println(proxy.out(null)); //拦截test，输出Hello cglib
		System.out.println(proxy.toString());
		System.out.println(proxy.getClass());
		System.out.println(proxy.hashCode());
	}
	@Test
	public void testInvocationHandler() throws Exception{
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Target.class);
		//设置单一回调对象，在调用中拦截对目标方法的调用
		enhancer.setCallback(new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				if(method.getDeclaringClass() != Object.class && method.getReturnType() == String.class){
					return "hello cglib";
				}else{
					throw new RuntimeException("Do not know what to do");
				}
			}
		});
		Target proxy = (Target) enhancer.create();
		assertEquals("hello cglib", proxy.out(null));
//		Assert.assertNotEquals("Hello cglib", proxy.toString());
	}

	/**
	 * 有些时候我们可能只想对特定的方法进行拦截，对其他的方法直接放行，
	 * 不做任何操作，使用Enhancer处理这种需求同样很简单,只需要一个CallbackFilter即可
	 * @throws Exception
	 */
	@Test
	public void testCallbackFilter() throws Exception{
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\111");
		Enhancer enhancer = new Enhancer();
		CallbackHelper callbackHelper = new CallbackHelper(Target.class, new Class[0]) {
			@Override
			protected Object getCallback(Method method) {
				if(method.getDeclaringClass() != Object.class && method.getReturnType() == String.class){
					return new FixedValue() {
						@Override
						public Object loadObject() throws Exception {
							return "Hello cglib";
						}
					};
				}else{
					return NoOp.INSTANCE;
				}
			}
		};
		enhancer.setSuperclass(Target.class);
		enhancer.setCallbackFilter(callbackHelper);
		enhancer.setCallbacks(callbackHelper.getCallbacks());
		Target proxy = (Target) enhancer.create();
		System.out.println(proxy.out(null));
		System.out.println(proxy.toString());
		System.out.println(proxy.hashCode());
	}
	@Test
	public void testCallbackFilter1() throws Exception{
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\111");
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Target.class);
		enhancer.setCallbackFilter(new CallBackFilterTest());
		enhancer.setCallbacks(new Callback[] {new Methodinterceptorimpl(),NoOp.INSTANCE,new Methodinterceptorimpl1()});
		Target proxy = (Target) enhancer.create();
		System.out.println(proxy.out(null));
		proxy.test2();
	}
	@Test
	public void testImmutableBean() {
		Target bean = new Target();
		bean.setValue("Hello world");
		Target immutableBean = (Target) ImmutableBean.create(bean); //创建不可变类
		assertEquals("Hello world",immutableBean.getValue());
		bean.setValue("Hello world, again"); //可以通过底层对象来进行修改
		assertEquals("Hello world, again", immutableBean.getValue());
		immutableBean.setValue("Hello cglib"); //直接修改将throw exception
	}
	@Test
	public void testBeanGenerator() throws Exception{
		BeanGenerator beanGenerator = new BeanGenerator();
		beanGenerator.addProperty("value",String.class);
		Object myBean = beanGenerator.create();
		Method setter = myBean.getClass().getMethod("setValue",String.class);
		setter.invoke(myBean,"Hello cglib");

		Method getter = myBean.getClass().getMethod("getValue");
		assertEquals("Hello cglib",getter.invoke(myBean));
	}

	/**
	 * cglib提供的能够从一个bean复制到另一个bean中，而且其还提供了一个转换器，用来在转换的时候对bean的属性进行操作。
	 * @throws Exception
	 */
	@Test
	public void testBeanCopier() throws Exception{
		BeanCopier copier = BeanCopier.create(Target.class, OtherTarget.class, false);//设置为true，则使用converter
		Target myBean = new Target();
		myBean.setValue("Hello cglib");
		OtherTarget otherBean = new OtherTarget();
		copier.copy(myBean, otherBean, null); //设置为true，则传入converter指明怎么进行转换
		assertEquals("Hello cglib", otherBean.getValue());
	}

	/**
	 * 相比于BeanCopier，BulkBean将copy的动作拆分为getPropertyValues和setPropertyValues两个方法，
	 * 允许自定义处理属性
	 * @throws Exception
	 */
	@Test
	public void testBulkBean() throws Exception{
		BulkBean bulkBean = BulkBean.create(OtherTarget.class,
				new String[]{"getValue"},
				new String[]{"setValue"},
				new Class[]{String.class});
		OtherTarget bean = new OtherTarget();
		bean.setValue("Hello world");
		Object[] propertyValues = bulkBean.getPropertyValues(bean);
		assertEquals(1, bulkBean.getPropertyValues(bean).length);
		assertEquals("Hello world", bulkBean.getPropertyValues(bean)[0]);
		bulkBean.setPropertyValues(bean,new Object[]{"Hello cglib"});
		assertEquals("Hello cglib", bean.getValue());
	}

	/**
	 * BeanMap类实现了Java Map，将一个bean对象中的所有属性转换为一个String-to-Obejct的Java Map
	 * @throws Exception
	 */
	@Test
	public void testBeanMap() throws Exception{
		BeanGenerator generator = new BeanGenerator();
		generator.addProperty("username",String.class);
		generator.addProperty("password",String.class);
		Object bean = generator.create();
		Method setUserName = bean.getClass().getMethod("setUsername", String.class);
		Method setPassword = bean.getClass().getMethod("setPassword", String.class);
		setUserName.invoke(bean, "admin");
		setPassword.invoke(bean,"password");
		BeanMap map = BeanMap.create(bean);
		Assert.assertEquals("admin", map.get("username"));
		Assert.assertEquals("password", map.get("password"));
	}

	/**
	 * keyFactory类用来动态生成接口的实例，接口需要只包含一个newInstance方法，返回一个Object。keyFactory为构造出来
	 * 的实例动态生成了Object.equals和Object.hashCode方法，能够确保相同的参数构造出的实例为单例的
	 * @throws Exception
	 */
	@Test
	public void testKeyFactory() throws Exception{
		SampleKeyFactory keyFactory = (SampleKeyFactory) KeyFactory.create(SampleKeyFactory.class);
		Object key = keyFactory.newInstance("foo", 42);
		Object key1 = keyFactory.newInstance("foo", 42);
		Assert.assertEquals(key,key1);//测试参数相同，结果是否相等
	}

	@Test
	public void testAbstract() throws Exception{
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\111");
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(AbstractClass.class);
		enhancer.setCallback(new Methodinterceptorimpl());
		AbstractClass proxy = (AbstractClass) enhancer.create();
		proxy.test();
		proxy.abstractTest();
	}

}
