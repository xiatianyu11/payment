package com.my.payment.account.dao.impl;

import org.springframework.stereotype.Repository;

import com.my.payment.account.dao.AccountDao;
import com.my.payment.account.entity.AccountDto;
import com.my.payment.base.dao.impl.BaseDaoImpl;

@Repository("accountDao")
public class AccountDaoImpl extends BaseDaoImpl<AccountDto> implements AccountDao {
	
	

	@Override
	public String buildAccountNo(String accountType) {
		String accountNoSeq = this.getSeqNextValue("ACCOUNT_NO_SEQ");
		String accountNo = "8008" + accountType.toUpperCase() + accountNoSeq + "0101";
		
		return accountNo;
	}

	@Override
	public AccountDto getByAccountNo(String accountNo) {
		// TODO Auto-generated method stub
		AccountDto dto  = new AccountDto();
		dto.setAccountNo(accountNo);
		return this.getRow(dto);
	}

	@Override
	public AccountDto getByUserNo(String userNo) {
		AccountDto dto  = new AccountDto();
		dto.setUserNo(userNo);
		return this.getRow(dto);
	}
	
	private String getSeqNextValue(String seqName){
		return (String)getSqlSession().selectOne("Account.SeqNextValue", seqName);
	}

}
