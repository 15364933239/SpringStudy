package com.lpt.test;


import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lpt.bean.User;
import com.lpt.dao.UserDao;
import com.lpt.dao.UserDaoImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext2.xml")
public class JdbcTest {
	@Resource(name="userDao")
	private UserDao ud;
	
	@Test
	public void test1() {
		User user = ud.selectUserById(1);
		System.out.println(user);
	}
	
	@Test
	public void test2() {
		ud.deleteUserById(4);
	}
	
	
	@Test
	public void test3() {
		User user = new User();
		user.setU_username("老王");
		user.setU_password("123");
		ud.saveUser(user);
	}
	
	@Test
	public void test4() {
		User user = new User();
		user.setU_id(5);
		user.setU_username("老李");
		user.setU_password("111253");
		ud.updateUser(user);
	}
	
	@Test
	public void test5() {
		User user = ud.selectUserById(5);
		System.out.println(user);
	}
	
	@Test
	public void test6() {
		List<User> list = ud.selectUserAll();
		for (User user : list) {
			System.out.println(user);
		}
	}
	
	@Test
	public void test7() {
		
		Integer count = ud.selectUserCount();
		System.out.println(count);
	}


}
