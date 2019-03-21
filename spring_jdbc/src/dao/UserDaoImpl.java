package com.lpt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.lpt.bean.User;

public class UserDaoImpl extends JdbcDaoSupport implements UserDao{
//	private static ComboPooledDataSource dataSource;
//	static {
//		//配置c3p0
//		try {
//			//连接数据库  c3p0
//			dataSource = new ComboPooledDataSource();
//			dataSource.setDriverClass("com.mysql.jdbc.Driver");
//			dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/ssm_mybatis");
//			dataSource.setUser("root");
//			dataSource.setPassword("root");
//		} catch (PropertyVetoException e) {
//			e.printStackTrace();
//		}
//		
//	}
	
	@Override
	public User selectUserById(Integer id) {
		String sql = "select * from user2 where u_id = ?";
		User user1 = getJdbcTemplate().queryForObject(sql, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int index) throws SQLException {
				User user = new User();
				user.setU_id(rs.getInt("u_id"));
				user.setU_username(rs.getString("u_username"));
				user.setU_password(rs.getString("u_password"));
				return user;
			}
			
		}, id);
		return user1;
	}

	//保存
	@Override
	public void saveUser(User user) {

		String sql = "insert into user2 values(null,?,?)";
		getJdbcTemplate().update(sql, user.getU_username(),user.getU_password());
		
	}

	//通过id删除用户
	@Override
	public void deleteUserById(Integer id) {
		String sql = "delete from user2 where u_id = ?";
		getJdbcTemplate().update(sql, id);
		
	}

	//更新用户
	@Override
	public void updateUser(User user) {
		String sql = "update user2 set u_username = ? , u_password = ? where u_id =?";
		getJdbcTemplate().update(sql, user.getU_username(),user.getU_password(),user.getU_id());
		
	}
	
	
    //查询用户列表
	@Override 
	public List<User> selectUserAll() {
		String sql = "select * from user2";
		List<User> list = getJdbcTemplate().query(sql, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int index) throws SQLException {
				User user = new User();
				user.setU_id(rs.getInt("u_id"));
				user.setU_username(rs.getString("u_username"));
				user.setU_password(rs.getString("u_password"));
				return user;
			}
			
		});
		return list;
	}

	//查询用户数量
	@Override
	public Integer selectUserCount() {
		String sql = "select count(*) from user2";
		return getJdbcTemplate().queryForObject(sql, Integer.class);
	}

//	public void setJt(JdbcTemplate jt) {
//		this.jt = jt;
//	}
	
	

}
