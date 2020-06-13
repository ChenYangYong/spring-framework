package aoptest.cglib;

import org.junit.Test;
import org.springframework.cglib.proxy.Mixin;

import static org.junit.Assert.assertEquals;

public class MixinInterfaceTest {
	interface Interface1{
		String first();
	}
	interface Interface2{
		String second();
	}

	class Class1 implements Interface1{
		@Override
		public String first() {
			return "first";
		}
	}

	class Class2 implements Interface2{
		@Override
		public String second() {
			return "second";
		}
	}

	interface MixinInterface extends Interface1, Interface2{

	}

	@Test
	public void testMixin() throws Exception{
		Mixin mixin = Mixin.create(new Class[]{MixinInterfaceTest.Interface1.class, MixinInterfaceTest.Interface2.class,
				MixinInterfaceTest.MixinInterface.class}, new Object[]{new MixinInterfaceTest.Class1(),new MixinInterfaceTest.Class2()});
		MixinInterfaceTest.MixinInterface mixinDelegate = (MixinInterfaceTest.MixinInterface) mixin;
		assertEquals("first", mixinDelegate.first());
		assertEquals("second", mixinDelegate.second());
	}
}