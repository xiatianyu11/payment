package com.my.payment.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.payment.account.dao.AccountDao;
import com.my.payment.account.dao.AccountHistoryDao;
import com.my.payment.account.entity.AccountDto;
import com.my.payment.account.entity.AccountHistoryDto;
import com.my.payment.account.entity.DailyCollectAccountHistoryVo;
import com.my.payment.account.exception.AccountBizException;
import com.my.payment.account.service.AccountQueryFacade;
import com.my.payment.base.dto.PageDto;
import com.my.payment.base.dto.Pagination;
@Service("accountQueryFacade")
public class AccountQueryFacadeImpl implements AccountQueryFacade {
	
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private AccountHistoryDao accountHistoryDao;

	@Override
	public Pagination queryAccountHistoryListPage(PageDto pageDto)
			throws AccountBizException {
		// TODO Auto-generated method stub
		return accountHistoryDao.findObjectsWithPg(pageDto);
	}

	@Override
	public AccountHistoryDto getAccountHistoryByAccountNo_requestNo_trxType(
			String accountNo, String requestNo, Integer trxType) {
		// TODO Auto-generated method stub
		return accountHistoryDao.getByAccountNo_requestNo_trxType(accountNo, requestNo, trxType);
	}

	@Override
	public AccountDto getAccountByUserNo(String userNo)
			throws AccountBizException {
		// TODO Auto-generated method stub
		return accountDao.getByUserNo(userNo);
	}

	@Override
	public AccountDto getAccountByAccountNo(String accountNo)
			throws AccountBizException {
		// TODO Auto-generated method stub
		return accountDao.getByAccountNo(accountNo);
	}

	@Override
	public List<DailyCollectAccountHistoryVo> listDailyCollectAccountHistoryVo(
			String accountNo, String statDate, Integer riskDay,
			Integer fundDirection) throws AccountBizException {
		// TODO Auto-generated method stub
		return accountHistoryDao.listDailyCollectAccountHistoryVo(accountNo, statDate, riskDay, fundDirection);
	}

	@Override
	public List<DailyCollectAccountHistoryVo> listDailyCollectAccountHistoryVo_t0(
			String accountNo, String requestNo) throws AccountBizException {
		// TODO Auto-generated method stub
		return accountHistoryDao.listDailyCollectAccountHistoryVo_t0(accountNo, requestNo);
	}

}
