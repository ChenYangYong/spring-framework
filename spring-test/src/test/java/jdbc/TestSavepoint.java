package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Savepoint;
import java.sql.Statement;

/**
 * 保存点就是这样将一整个完整的过程进行了拆分，rollback到哪个保存点，
 * 哪个保存点以下就会回滚，之前的就会提交
 *
 * 一定要注意： conn.rollback(savepoint1); 并不会结束事务，
 * 只有 conn.commit();或者 conn.rollback();方法才会结束事务。
 */
public class TestSavepoint {
	public static void main(String[] args) throws Exception{
		String user = "root";
		String password = "123456";
		String url = "jdbc:mysql://localhost:3306/test01?useUnicode=true&characterEncoding=utf-8";
		//2、获取连接对象
		Connection conn = DriverManager.getConnection(url, user, password);
		conn.setAutoCommit(false);
		String sql1 = "delete from user where id='1'";
		String sql2 = "delete from user where id='2'";
		//2、获得sql语句执行对象
		Statement stmt = conn.createStatement();
		//3、执行并保存结果集
		stmt.executeUpdate(sql1);
		Savepoint savepoint1 = conn.setSavepoint("savepoint1");
		stmt.executeUpdate(sql2);
		Savepoint savepoint2 = conn.setSavepoint("savepoint2");
		conn.rollback(savepoint1);
		conn.commit();
		// conn.rollback();
		conn.close();
		stmt.close();
	}
}