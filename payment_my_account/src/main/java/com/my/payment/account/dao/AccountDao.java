package com.my.payment.account.dao;

import com.my.payment.account.entity.AccountDto;

public interface AccountDao {
	
	public String buildAccountNo(String accountType);
	
	public AccountDto getByAccountNo(String accountNo);
	
	public AccountDto getByUserNo(String userNo);

}
