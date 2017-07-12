package com.my.payment.account.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.my.payment.account.dao.AccountDao;
import com.my.payment.account.dao.AccountHistoryDao;
import com.my.payment.account.entity.AccountDto;
import com.my.payment.account.entity.AccountTransactionVo;
import com.my.payment.account.enums.AccountFundDirectionEnum;
import com.my.payment.account.enums.AccountTradeTypeEnum;
import com.my.payment.account.enums.AccountTypeEnum;
import com.my.payment.account.exception.AccountBizException;
import com.my.payment.account.service.AccountSettFacade;
import com.my.payment.account.service.AccountTransactionFacade;



@Service("accountSettFacade")
public class AccountSettFacadeImpl implements AccountSettFacade {

	@Autowired
	private AccountHistoryDao accountHistoryDao;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private AccountTransactionFacade accountTransactionFacade;

	private static final Log log = LogFactory.getLog(AccountSettFacadeImpl.class);

	/**
	 * 结算创建_T+0
	 * 
	 * @param userNo
	 * @param settAmount
	 * @param requestNo 结算请求
	 * @param trxNo 账户历史交易请求
	 */
	public void settCreateT0(String userNo, Double settAmount, String requestNo ,String trxNo) {
		AccountDto account = accountDao.getByUserNo(userNo);
		if (account == null) {
			throw AccountBizException.ACCOUNT_NOT_EXIT.newInstance("账户不存在,用户编号{%s}", userNo).print();
		}

		List<AccountTransactionVo> voList = new ArrayList<AccountTransactionVo>();

		// 账户资金冻结
		AccountTransactionVo vo = new AccountTransactionVo();
		vo.setAccountFundDirection(AccountFundDirectionEnum.FROZEN);
		vo.setUserNo(userNo);
		vo.setFrezonAmount(settAmount);
		vo.setRequestNo(requestNo);
		vo.setTradeType(AccountTradeTypeEnum.SETTLEMENT);
		vo.setDesc("资金冻结");
		voList.add(vo);

		accountTransactionFacade.execute(voList);

		accountHistoryDao.updateCompleteSettTo100_t0(account.getAccountNo(), trxNo);
	}

	/**
	 * 结算成功
	 * 
	 * @param userNo
	 * @param settAmount
	 * @param requestNo
	 */
	public void settSuccess(String userNo, Double settAmount, String requestNo) {
		AccountDto account = accountDao.getByUserNo(userNo);
		if (account == null) {
			throw AccountBizException.ACCOUNT_NOT_EXIT.newInstance("账户不存在,用户编号{%s}", userNo).print();
		}

		List<AccountTransactionVo> voList = new ArrayList<AccountTransactionVo>();

		AccountTradeTypeEnum accountTradeType = AccountTradeTypeEnum.SETTLEMENT;
		if (account.getAccountType() == AccountTypeEnum.CUSTOMER.getValue()) {
			accountTradeType = AccountTradeTypeEnum.ATM;
		}

		// 虚拟账户解冻+减款
		AccountTransactionVo vo = new AccountTransactionVo();
		vo.setAccountFundDirection(AccountFundDirectionEnum.UNFROZEN);
		vo.setUserNo(userNo);
		vo.setUnFrezonAmount(settAmount);
		vo.setRequestNo(requestNo);
		vo.setTradeType(accountTradeType);
		vo.setDesc("资金解冻");
		voList.add(vo);

		vo = new AccountTransactionVo();
		vo.setAccountFundDirection(AccountFundDirectionEnum.SUB);
		vo.setUserNo(userNo);
		vo.setAmount(settAmount);
		vo.setRequestNo(requestNo);
		vo.setTradeType(accountTradeType);
		vo.setDesc(accountTradeType.getDesc());
		voList.add(vo);

		accountTransactionFacade.execute(voList);
	}

	/**
	 * 结算汇总成功
	 * 
	 * @param userNo
	 * @param statDate
	 * @param riskDay
	 */
	public void settCollectSuccess(String userNo, String statDate, Integer riskDay) {
		AccountDto account = accountDao.getByUserNo(userNo);
		if (account == null) {
			throw AccountBizException.ACCOUNT_NOT_EXIT.newInstance("账户不存在,用户编号{%s}", userNo).print();
		}

		accountHistoryDao.updateCompleteSettTo100(account.getAccountNo(), statDate, riskDay);
	}

	/**
	 * 结算创建
	 * 
	 * @param userNo
	 * @param settAmount
	 * @param requestNo
	 * @param lastId
	 */
	public void settCreate(String userNo, Double settAmount, String requestNo, Long lastId) {
		AccountDto account = accountDao.getByUserNo(userNo);
		if (account == null) {
			throw AccountBizException.ACCOUNT_NOT_EXIT.newInstance("账户不存在,用户编号{%s}", userNo).print();
		}

		AccountTradeTypeEnum accountTradeType = AccountTradeTypeEnum.SETTLEMENT;
		if (account.getAccountType() == AccountTypeEnum.CUSTOMER.getValue()) {
			accountTradeType = AccountTradeTypeEnum.ATM;
		}

		// 账户资金冻结
		AccountTransactionVo vo = new AccountTransactionVo();
		vo.setAccountFundDirection(AccountFundDirectionEnum.FROZEN);
		vo.setUserNo(userNo);
		vo.setFrezonAmount(settAmount);
		vo.setRequestNo(requestNo);
		vo.setTradeType(accountTradeType);
		vo.setDesc("资金冻结");

		accountTransactionFacade.execute(vo);

		accountHistoryDao.updateCompleteSettTo100LastId(account.getAccountNo(), lastId);
	}

	/**
	 * 结算失败
	 * 
	 * @param userNo
	 * @param settAmount
	 * @param requestNo
	 */
	public void settFail(String userNo, Double settAmount, String requestNo) {
		AccountDto account = accountDao.getByUserNo(userNo);
		if (account == null) {
			throw AccountBizException.ACCOUNT_NOT_EXIT.newInstance("账户不存在,用户编号{%s}", userNo).print();
		}

		List<AccountTransactionVo> voList = new ArrayList<AccountTransactionVo>();

		AccountTradeTypeEnum accountTradeType = AccountTradeTypeEnum.SETTLEMENT;
		if (account.getAccountType() == AccountTypeEnum.CUSTOMER.getValue()) {
			accountTradeType = AccountTradeTypeEnum.ATM;
		}

		// 虚拟账户解冻
		AccountTransactionVo vo = new AccountTransactionVo();
		vo.setAccountFundDirection(AccountFundDirectionEnum.UNFROZEN);
		vo.setUserNo(userNo);
		vo.setUnFrezonAmount(settAmount);
		vo.setRequestNo(requestNo);
		vo.setTradeType(accountTradeType);
		vo.setDesc("资金解冻");
		voList.add(vo);

		accountTransactionFacade.execute(voList);
	}

}
