package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestTransaction {
	public static Connection getConnection() throws Exception{
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn=(Connection) DriverManager.getConnection("jdbc:mysql://10.23.13.106:3306/ptest?useSSL=FALSE&serverTimezone=UTC","root","EMall@1234");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	public static void insertUser(Connection conn) throws SQLException {
		String sql=" insert into user(name , age , sex)values('yancy','29','30')";
		Statement st=conn.createStatement();
		int count=st.executeUpdate(sql);
		System.out.println("向用户表插入了"+count+"条记录！");
	}
	public static void insertCity(Connection conn) throws SQLException {
		String sql="insert into city(id,country_code,district,name,population)"+
				"values(1,'222','bbbbbbbb','bbbbbb','3333')";
		Statement st=conn.createStatement();
		int count=st.executeUpdate(sql);
		System.out.println("向城市表插入了"+count+"条记录！");
	}
	public static void main(String[] args) throws Exception{
		Connection conn =getConnection();
		try {
			//开启手动事务的关键是con.setAutoCommit(false)，JDBC事务默认是开启的，并且是自动提交：
			conn.setAutoCommit(true);
			insertUser(conn);
			//插入报错，id重复
			insertCity(conn);
			conn.commit();
		} catch (SQLException e) {
			System.out.println("************事务处理出现异常***********");
			e.printStackTrace();
			try {
				conn.rollback();
				System.out.println("*********事务回滚成功***********");
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}finally {
				try {
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}