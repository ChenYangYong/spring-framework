package aoptest.targetsource;

import org.springframework.aop.TargetSource;

public class MyTargetSource implements TargetSource {
	private ITask task1;
	private ITask task2;

	private int counter;

	public MyTargetSource(ITask task1, ITask task2) {
		this.task1 = task1;
		this.task2 = task2;
	}

	@Override
	public Class<?> getTargetClass() {
		return ITask.class;
	}

	@Override
	public boolean isStatic() {
		return false;
	}

	@Override
	public Object getTarget() throws Exception {
		try {
			if(counter%2==0){
				return task2;
			}else {
				return task1;
			}
		}finally {
			counter++;
		}
	}

	@Override
	public void releaseTarget(Object target) throws Exception {

	}
}
