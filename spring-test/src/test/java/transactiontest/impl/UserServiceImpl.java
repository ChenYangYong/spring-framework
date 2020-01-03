package transactiontest.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import transactiontest.User;
import transactiontest.UserService;

import javax.sql.DataSource;
import java.sql.Types;

public class UserServiceImpl implements UserService {

	private JdbcTemplate jdbcTemplate;

	//设置数据源
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void save(User user) throws Exception {
		jdbcTemplate.update(" insert into user(name , age , sex)values(?,?,?)",
				new Object[]{user.getName(), user.getAge(), user.getSex()},
				new int[]{java.sql.Types.VARCHAR, java.sql.Types.INTEGER, java.sql.Types.VARCHAR});
		//事务测试， 加上这句代码y!IJ数据不会保存到数据库中
//		throw new RuntimeException("aa");

	}

}
