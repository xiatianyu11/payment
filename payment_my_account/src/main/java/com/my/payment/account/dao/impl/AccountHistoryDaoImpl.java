package com.my.payment.account.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.my.payment.account.dao.AccountHistoryDao;
import com.my.payment.account.entity.AccountHistoryDto;
import com.my.payment.account.entity.DailyCollectAccountHistoryVo;
import com.my.payment.base.dao.impl.BaseDaoImpl;

@Repository("accountHistoryDao")
public class AccountHistoryDaoImpl extends BaseDaoImpl<AccountHistoryDto> implements AccountHistoryDao {

	@Override
	public AccountHistoryDto getByAccountNo_requestNo_trxType(String accountNo,
			String requestNo, Integer trxType) {
		// TODO Auto-generated method stub
		AccountHistoryDto dto = new AccountHistoryDto();
		dto.setAccountNo(accountNo);
		dto.setRequestNo(requestNo);
		dto.setTrxType(trxType);
		return this.getRow(dto);
	}
	
	/**
	 * 日汇总账户待结算金额
	 * 
	 * @param accountNo
	 * @param statDate
	 * @param riskDay
	 * @param fundDirection
	 * @return
	 */
	public List<DailyCollectAccountHistoryVo> listDailyCollectAccountHistoryVo(String accountNo, String statDate, Integer riskDay,
			Integer fundDirection) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountNo", accountNo);
		params.put("statDate", statDate);
		params.put("riskDay", riskDay);
		params.put("fundDirection", fundDirection);
		return getSqlSession().selectList("AccountHistory.listDailyCollectAccountHistoryVo", params);
	}
	
	/**
	 * 日汇总账户待结算金额_针对单笔t+0结算
	 * 
	 * @param accountNo
	 * @param requestNo
	 */
	public List<DailyCollectAccountHistoryVo> listDailyCollectAccountHistoryVo_t0(String accountNo, String requestNo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountNo", accountNo);
		params.put("requestNo", requestNo);
		return getSqlSession().selectList("AccountHistory.listDailyCollectAccountHistoryVo_t0", params);
	}
	
	/**
	 * 更新账户风险预存期外的账户历史记录记为结算完成
	 * 
	 * @param accountNo
	 * @param statDate
	 * @param riskDay
	 */
	public void updateCompleteSettTo100(String accountNo, String statDate, Integer riskDay) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountNo", accountNo);
		params.put("statDate", statDate);
		params.put("riskDay", riskDay);
		getSqlSession().update("AccountHistory.updateCompleteSettTo100", params);
	}
	
	/**
	 * 更新账户历史记录记为结算完成_针对单笔t+0结算
	 * 
	 * @param accountNo
	 * @param requestNo
	 */
	public void updateCompleteSettTo100_t0(String accountNo, String requestNo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountNo", accountNo);
		params.put("requestNo", requestNo);
		getSqlSession().update("AccountHistory.updateCompleteSettTo100_t0", params);
	}
	
	/**
	 * 更新账户历史记录记为结算完成
	 * 
	 * @param accountNo
	 * @param lastId
	 */
	public void updateCompleteSettTo100LastId(String accountNo, Long lastId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountNo", accountNo);
		params.put("lastId", lastId);
		getSqlSession().update("AccountHistory.updateCompleteSettTo100LastId", params);
	}



}
