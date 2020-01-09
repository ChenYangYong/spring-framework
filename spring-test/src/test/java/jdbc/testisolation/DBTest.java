package jdbc.testisolation;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/*
https://blog.csdn.net/crow_feiyu/article/details/51305826
 */
public class DBTest {
	private String url ;
	private String user;
	private String password;

	/**
	 * 创建数据连接
	 * @return
	 */
	private Connection getCon(){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/test01?useSSL=FALSE&serverTimezone=UTC";
			user = "root";
			password = "123456";
			con = DriverManager.getConnection(url, user, password);
		}catch (Exception e){
			e.printStackTrace();
			try {
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return con;
	}

	/**
	 * 通过链接获取声明
	 * @param con
	 * @return
	 */
	private Statement getStat(Connection con){
		Statement state = null;
		try{
			state = con.createStatement();
		}catch(Exception e){
			e.printStackTrace();
		}
		return state;
	}

	/**
	 *  打印数据库所有数据
	 */
	public void selectAll(int transactionType){
		Connection con = null;
		Statement state = null;
		ResultSet rs = null;
		try{
			con = getCon();
			if(transactionType >= 0 ){
				con.setTransactionIsolation(transactionType);
			}
			System.out.println("selectAll方法-------------当前事务隔离级别为："+con.getTransactionIsolation()+"---"+ DateFormatUtils.format(new Date(),"yyyyMMdd HH:mm:ss SSS"));
			state = getStat(con);
			rs = state.executeQuery("select * from t_dict");
			ResultSetMetaData rsmd = rs.getMetaData();
			for(int i = 1;i<= rsmd.getColumnCount() ;i++){
				System.out.print(rsmd.getColumnName(i)+"| ");
			}
			System.out.println();
			System.out.println("-------------------------------------------");
			//打印所有行
			while(rs.next()){
				for(int i = 1;i<= rsmd.getColumnCount() ;i++){
					System.out.print(rs.getString(i)+"|     ");
				}
				System.out.println();
			}
		}
		catch (Exception e){
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null){
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(state != null){
					state.close();
				}
			} catch (Exception e){

			}
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 新增一行
	 * @param needExcepition
	 * @param sleepTimes
	 * @param values
	 * @return
	 */
	public int insertOne(int needExcepition,int sleepTimes, List<String> values){
		Connection con = getCon();
		PreparedStatement pre = null;
		String sql = "INSERT INTO t_dict (dict_type, dict_code, dict_name, dict_remark) VALUES (?, ?, ?, ?)";
		int res = 0;
		try {
			con.setAutoCommit(false);
			pre = con.prepareStatement(sql);
			for(int i = 0; i < values.size() ;i++){
				pre.setString(i+1, values.get(i));
			}
//			Thread.sleep(sleepTimes);
			res = pre.executeUpdate();
			System.out.println("insertOne方法execute "+ DateFormatUtils.format(new Date(),"yyyyMMdd HH:mm:ss SSS"));
			Thread.sleep(sleepTimes);
			int i = 1/needExcepition;
			con.commit();
			System.out.println("insertOne方法commit "+ DateFormatUtils.format(new Date(),"yyyyMMdd HH:mm:ss SSS"));
		} catch (Exception e) {
			try {
				con.rollback();
				System.out.println("insertOne方法roll back "+ DateFormatUtils.format(new Date(),"yyyyMMdd HH:mm:ss SSS"));
				res = 0;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if(pre != null){
					pre.close();
				}
			} catch (Exception e){

			}
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}

	/**
	 * 间隔一定时间读取多次 第一次读取线程不等待
	 * @param dictType 要去读取的数据类型
	 * @param sleepTimes 每次读取之间的间隔时间
	 * @param printTimes 打印次数
	 * @param transactionType 事务隔离级别
	 */
	private void printMultiple(String dictType,int sleepTimes,int printTimes, int transactionType){
		Connection con = null;
		Statement state = null;
		ResultSet rs = null;
		try{
			con = getCon();
			con.setAutoCommit(false);
			if(transactionType >= 0){
				con.setTransactionIsolation(transactionType);
			}
			System.out.println("printMultiple方法-------------当前事务隔离级别为："+con.getTransactionIsolation()+"---"+ DateFormatUtils.format(new Date(),"yyyyMMdd HH:mm:ss SSS"));
			state = getStat(con);
			String sql = "select * from t_dict";
			if(StringUtils.isNoneBlank(dictType)){
				sql = sql + " where dict_type = '"+dictType+"' ";
			}
			for (int j = 0; j < printTimes; j++) {
				if(j!=0){
					Thread.sleep(sleepTimes);
				}
				//为验证TRANSACTION_REPEATABLE_READ中的幻读，进行一次修改
				if(j!=0 && transactionType==4 && StringUtils.isBlank(dictType)){
					System.out.println("=============幻读模拟更新========");
					String updateSql = "UPDATE t_dict  SET dict_code = '33', dict_name = 'type33', dict_remark = 'type33' WHERE dict_type ='type0'";
					state.execute(updateSql);
				}
				rs = state.executeQuery(sql);
				ResultSetMetaData rsmd = rs.getMetaData();
				System.out.println("printMultiple方法-第"+(j+1)+"次读取"+ DateFormatUtils.format(new Date(),"yyyyMMdd HH:mm:ss SSS"));
				for(int i = 1;i<= rsmd.getColumnCount() ;i++){
					System.out.print(rsmd.getColumnName(i)+"| ");
				}
				System.out.println();
				System.out.println("-------------------------------------------");
				while(rs.next()){
					for(int i = 1;i<= rsmd.getColumnCount() ;i++){
						System.out.print(rs.getString(i)+"|     ");
					}
					System.out.println();
				}
			}
			con.commit();
		}
		catch (Exception e){
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null){
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(state != null){
					state.close();
				}
			} catch (Exception e){

			}
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 更改一条数据的内容
	 * @param dict_type
	 * @param sleepTimes
	 * @param values
	 * @return
	 */
	public int updateOne(String dict_type,int sleepTimes, List<String> values){
		Connection con = null;
		PreparedStatement pre = null;
		String sql = "UPDATE t_dict  SET dict_code = ?, dict_name = ?, dict_remark = ? WHERE dict_type ='"+dict_type+"'";
		int res = 0;
		try {
			con = getCon();
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
			pre = con.prepareStatement(sql);
			for(int i = 0; i < values.size() ;i++){
				pre.setString(i+1, values.get(i));
			}
			Thread.currentThread().sleep(sleepTimes);
			res = pre.executeUpdate();
			System.out.println("updateOne方法execute "+ DateFormatUtils.format(new Date(),"yyyyMMdd HH:mm:ss SSS"));
//			Thread.currentThread().sleep(sleepTimes);
			con.commit();
			System.out.println("updateOne方法commit "+ DateFormatUtils.format(new Date(),"yyyyMMdd HH:mm:ss SSS"));
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if(pre != null){
					pre.close();
				}
			} catch (Exception e){

			}
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}



	/**
	 * 初始化数据
	 */
	private void intDate(){
		System.out.println("------------初始化数据 start-------------");
		Connection con = getCon();
		Statement pre = null;
		String sqlDelete = "delete from t_dict";
		String sqlInsert = "INSERT INTO `t_dict` (`dict_type`, `dict_code`, `dict_name`, `dict_remark`) VALUES ('type0', '00', 'type00', 'type00')";
		try {
			con.setAutoCommit(false);
			pre = con.createStatement();
			pre.execute(sqlDelete);
			pre.execute(sqlInsert);
			con.commit();
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if(pre != null){
					pre.close();
				}
			} catch (Exception e){
				e.printStackTrace();
			}
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("------------初始化数据 end-------------");
	}
	/**
	 * 模拟读脏，抛出未捕获异常，插入数据不提交
	 *
	 *读脏：一个事务读取另外一个事务尚未提交的数据。如当前目录下的Dirty.png，线程thread1在事务中在time1时刻
	 * 向库表中新增一条数据‘test’并在time3时刻回滚数据；线程thread2在time2时刻读取，若thread2
	 * 读取到‘test’，则为读脏
	 *
	 */
	public void testDirty(int transactionType){
		List<String> list = new ArrayList<String>();
		list.add("type1");
		list.add("11");
		list.add("type11");
		list.add("type11");
		TestThread testThread = new TestThread("insert",0,2000,list);
		Thread thread = new Thread(testThread);
		thread.start();
		try {
			Thread.currentThread().sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectAll(transactionType);
	}

	/**
	 * 模拟幻读，第N次读取多出数据
	 *
	 * 幻读：其他事务的数据操作导致某个事务两次读取数据数量不一致。如当前目录下的Trick.png，线程thread1在
	 * 事务中time1时刻向数据库中新增‘00’，并在time3时刻提交；thread2在一个事务中
	 * 分别在time2和time4两个时刻扫描库表，若两次读取结果不同则为幻读。
	 * （注意：1.幻读针对已经提交的数据。2.两次或多次读取不同行数据，数量上新增或减少。）
	 */
	public void testTrick(int transactionType){
		List<String> list = new ArrayList<String>();
		list.add("type2");
		list.add("22");
		list.add("type22");
		list.add("type22");
		//执行插入，不产生异常
		TestThread testThread = new TestThread("insert",1,1000,list);
		Thread thread = new Thread(testThread);
		thread.start();
		//打印两次
		printMultiple(null, 4000,2,transactionType);
	}

	/**
	 * 模拟不可重读，多次读取同一条记录，记录被更改
	 *
	 * 不可重新读：其他事务的操作导致某个事务两次读取数据不一致。如当前目录下的Repeat.png，线程thread1在事务中time1时刻将
	 * 数据库中‘test’更新为‘00’，并在time3时刻提交；thread2在一个事务中分别在time2和time4两个时刻读取这条记录，若两次读取结果不同则为不可重读。
	 * （注意：1.不可重读针对已经提交的数据。2.两次或多次读取同一条数据。）
	 */
	public void testRepeat(int transactionType){
		List<String> list = new ArrayList<String>();
		list.add("type0");
		list.add("11");
		list.add("type11");
		list.add("type11");
		//执行修改，不产生异常
		TestThread testThread = new TestThread("update",1,1000,list);
		Thread thread = new Thread(testThread);
		thread.start();
		//打印4次
		printMultiple("type0", 2000,2,transactionType);
	}

	/**
	 * @param transType
	 * 隔离级别对当前事务有效，例如若当前事务设置为TRANSACTION_READ_UNCOMMITTED，
	 * 则允许当前事务对其他事务未提交的数据进行读脏，而非其他事务可对当前事务未提交的数据读脏。
	 */
	public void testTransaction(int transType){
		intDate();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("========================================");
		selectAll(-1);
		System.out.println("========================================");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.err.println("-------------------读脏模拟---------------------"+ DateFormatUtils.format(new Date(),"yyyyMMdd HH:mm:ss SSS"));
		testDirty(transType);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("========================================");
		selectAll(-1);
		System.out.println("========================================");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.err.println("-------------------不可重读模拟------------------"+ DateFormatUtils.format(new Date(),"yyyyMMdd HH:mm:ss SSS"));
		testRepeat(transType);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("========================================");
		selectAll(-1);
		System.out.println("========================================");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.err.println("-------------------幻读模拟----------------------"+ DateFormatUtils.format(new Date(),"yyyyMMdd HH:mm:ss SSS"));
		testTrick(transType);
	}

	public static void main(String[] args){
		DBTest dbTest = new DBTest();
		/*分别执行下面的方法，即可模拟各个隔离级别下，线程并发事务间的访问结果*/

//		System.out.println("-----------------------TRANSACTION_READ_UNCOMMITTED(允许读脏，不可重读，幻读) test start------------------------");
//		dbTest.testTransaction(Connection.TRANSACTION_READ_UNCOMMITTED);

//      System.out.println("------------TRANSACTION_READ_COMMITTED(直译为仅允许读取已提交的数据，即不能读脏，但是可能发生不可重读和幻读) test start------------------------");
//      dbTest.testTransaction(Connection.TRANSACTION_READ_COMMITTED);
//
      System.out.println("------------TRANSACTION_REPEATABLE_READ(不可读脏，保证同一事务重复读取相同数据，但是可能发生幻读) test start------------------------");
      dbTest.testTransaction(Connection.TRANSACTION_REPEATABLE_READ);

//      System.out.println("------------TRANSACTION_SERIALIZABLE(直译为串行事务，保证不读脏，可重复读，不可幻读，事务隔离级别最高) test start------------------------");
//      dbTest.testTransaction(Connection.TRANSACTION_SERIALIZABLE);
//
//      System.out.println("------------default TRANSACTION_NONE(无事务)  test start------------------------");
//      dbTest.testTransaction(-1);
	}

}
