package com.my.payment.account.service;

import java.util.List;

import com.my.payment.account.entity.AccountDto;
import com.my.payment.account.entity.AccountHistoryDto;
import com.my.payment.account.entity.DailyCollectAccountHistoryVo;
import com.my.payment.account.exception.AccountBizException;
import com.my.payment.base.dto.PageDto;
import com.my.payment.base.dto.Pagination;

public interface AccountQueryFacade {
	
	/**
	 * 账户历史查询.
	 * 
	 * @return AccountHistoryList.
	 * @throws AccountBizException
	 */
	Pagination queryAccountHistoryListPage(PageDto pageDto) throws AccountBizException;

	/**
	 * 获取账户历史
	 * 
	 * @param accountNo
	 * @param requestNo
	 * @param trxType
	 * @return
	 */
	AccountHistoryDto getAccountHistoryByAccountNo_requestNo_trxType(String accountNo, String requestNo, Integer trxType);

	/**
	 * 根据用户编号获取账户信息 .
	 * 
	 * @param userNo
	 *            用户编号.
	 * @return account 查询到的账户信息.
	 * @throws AccountBizException
	 */
	AccountDto getAccountByUserNo(String userNo) throws AccountBizException;

	/**
	 * 根据账户编号查询账户信息.
	 * 
	 * @param accountNo
	 *            账户编号.
	 * @return account 查询到的账户信息.
	 * @throws AccountBizException
	 */
	AccountDto getAccountByAccountNo(String accountNo) throws AccountBizException;

	/**
	 * 日汇总账户待结算金额 .
	 * 
	 * @param accountNo
	 *            账户编号
	 * @param statDate
	 *            统计日期
	 * @param riskDay
	 *            风险预测期
	 * @param fundDirection
	 *            资金流向
	 * @return
	 * @throws AccountBizException
	 */
	List<DailyCollectAccountHistoryVo> listDailyCollectAccountHistoryVo(String accountNo, String statDate, Integer riskDay,
			Integer fundDirection) throws AccountBizException;

	/**
	 * 日汇总账户待结算金额_针对单笔t+0结算
	 * 
	 * @param accountNo
	 * @param requestNo
	 */
	List<DailyCollectAccountHistoryVo> listDailyCollectAccountHistoryVo_t0(String accountNo, String requestNo) throws AccountBizException;

}
