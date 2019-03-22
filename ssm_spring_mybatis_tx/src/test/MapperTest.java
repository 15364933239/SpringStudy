package com.lpt.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lpt.service.AccountService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MapperTest {

	@Resource(name="accountService")
	private AccountService as;
	
	@Test
	public void test1() {
		as.tranferAccount();
	}
}
