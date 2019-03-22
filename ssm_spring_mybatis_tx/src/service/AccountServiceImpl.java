package com.lpt.service;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lpt.bean.Account;
import com.lpt.mapper.AccountMapper;

@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
public class AccountServiceImpl implements AccountService {

	@Resource(type=AccountMapper.class)
	private AccountMapper mapper;
	
	@Override
	public void tranferAccount() {
		
		Double tranferMoney = 1000d;
		
		Account pay = new Account();
		pay.setId(1);
		pay.setTranferMoney(tranferMoney);
		
		//先扣钱
		mapper.subMoney(pay);
		
		Account add = new Account();
		add.setId(2);
		add.setTranferMoney(tranferMoney);
		
		int a = 1/0;
		
		//加钱
		mapper.addMoney(add);
		
	}

}
