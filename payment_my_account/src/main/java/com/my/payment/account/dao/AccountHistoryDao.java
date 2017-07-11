package com.my.payment.account.dao;

import java.util.List;

import com.my.payment.account.entity.AccountHistoryDto;
import com.my.payment.account.entity.DailyCollectAccountHistoryVo;
import com.my.payment.base.dao.BaseDao;

public interface AccountHistoryDao extends BaseDao<AccountHistoryDto>{
	
	/**
	 * 获取账户历史
	 * 
	 * @param accountNo
	 * @param requestNo
	 * @param trxType
	 * @return
	 */
	AccountHistoryDto getByAccountNo_requestNo_trxType(String accountNo, String requestNo, Integer trxType);
	
	
	public List<DailyCollectAccountHistoryVo> listDailyCollectAccountHistoryVo(String accountNo, String statDate, Integer riskDay, Integer fundDirection) ;


}
