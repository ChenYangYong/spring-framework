package yancy;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class TestListener  implements ApplicationListener {
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		System.out.println("--------进入TestListener的onApplicationEvent方法");
		if(event instanceof TestEvent){
			TestEvent testEvent = (TestEvent )event ;
			testEvent.print() ;
		}
	}
}
