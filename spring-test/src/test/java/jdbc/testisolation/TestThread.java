package jdbc.testisolation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ygl on 2016/5/1.
 */
public class TestThread implements Runnable {
	int needException = 1;

	int sleepTimes = 0;

	List<String> list = new ArrayList<String>();

	String method = "";

	DBTest dbTest = new DBTest();
	/**
	 * @param method insert 或 update
	 * @param needException 是否需要抛出异常，0抛出异常，1不抛出异常
	 * @param sleepTimes 线程睡眠时间（毫秒）
	 * @param list 更新数据库的数据，当method为update时，list的第一个元素为条件，其他为更新内容
	 */
	public TestThread(String method,int needException, int sleepTimes , List<String> list){
		this.needException = needException;
		this.sleepTimes = sleepTimes;
		this.list = list;
		this.method = method;
	}

	public void run(){
		if("insert".equals(method)){
			insert();
		} else if("update".equals(method)){
			update();
		}
	}

	private void insert(){
		int res = dbTest.insertOne(needException, sleepTimes, list);
		if(res == 1){
			System.out.println("insert success");
		}else{
			System.out.println("insert fail");
		}
	}

	private void update(){
		String updateKey = list.get(0);
		list.remove(0);
		int res = dbTest.updateOne(updateKey, sleepTimes, list);
		if(res == 1){
			System.out.println("update success");
		}else{
			System.out.println("update fail");
		}
	}
}
