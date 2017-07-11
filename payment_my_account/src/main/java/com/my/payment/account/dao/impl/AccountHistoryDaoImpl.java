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


}
