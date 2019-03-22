package com.lpt.mapper;

import com.lpt.bean.Account;

/**
 * 
 * @author lipeitong
 * 2019年3月22日 上午10:56:18
 * <dl>
 * 		<dd>AccountMapper</dd>
 * 		<dt>账户mapper接口</dt>
 * </dl>
 */
public interface AccountMapper {
	//操作数据库扣款加款
	
	//扣款
	void subMoney(Account pay);
	
	//加款
	void addMoney(Account add);
}
