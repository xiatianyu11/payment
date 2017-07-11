package com.my.payment.account.dao;

import com.my.payment.account.entity.AccountDto;
import com.my.payment.base.dao.BaseDao;

public interface AccountDao extends BaseDao<AccountDto>{
	
	public String buildAccountNo(String accountType);
	
	public AccountDto getByAccountNo(String accountNo);
	
	public AccountDto getByUserNo(String userNo);

}
