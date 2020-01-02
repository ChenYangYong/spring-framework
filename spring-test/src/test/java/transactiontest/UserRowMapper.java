package transactiontest;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

class UserRowMapper implements RowMapper {
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("user_id"));
		user.setName(rs.getString("name"));
		user.setSex(rs.getString("sex"));
		user.setAge(rs.getInt("age"));
		return user;
	}
}
