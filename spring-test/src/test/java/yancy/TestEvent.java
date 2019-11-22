package yancy;

import org.springframework.context.ApplicationEvent;

public class TestEvent extends ApplicationEvent {
	private String event;

	public TestEvent(Object source) {
		super(source);
	}

	public TestEvent(Object source, String event) {
		super(source);
		this.event = event;
	}
	public void print(){
		System.out.println("event="+event);
	}
}
