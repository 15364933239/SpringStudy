package com.lpt.dao;

import java.util.List;

import com.lpt.bean.User;

public interface UserDao {
	
	//增
	void saveUser(User user);
	//删
	void deleteUserById(Integer id);
	//改
	void updateUser(User user);
	//查  根据id查找用户
	User selectUserById(Integer id);
	//查找全部用户
	List<User> selectUserAll();
	//查找用户数量
	Integer selectUserCount();
	
	
}
