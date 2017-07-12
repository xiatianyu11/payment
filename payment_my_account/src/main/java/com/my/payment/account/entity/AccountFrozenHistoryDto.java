package com.my.payment.account.entity;

import com.my.payment.base.annotations.EntityPK;
import com.my.payment.base.dto.impl.BaseDtoImpl;

@EntityPK(Pk = "id", tableName = "TBL_ACCOUNT_FROZEN_HISTORY")
public class AccountFrozenHistoryDto extends BaseDtoImpl{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 请求号
	 */
	private String requestNo;
	/**
	 * 账户编号
	 */
	private String accountNo;
	/**
	 * 冻结/解冻金额
	 */
	private Double amount;
	/**
	 * 当前冻结金额
	 */
	private Double currentAmount;
	/**
	 * 描述
	 */
	private String remark;
	/**
	 * 冻结/解冻(AccountFrozenHistoryTypeEnum)
	 */
	private Integer accountFrozenHistoryType;
	/**
	 * 交易类型（枚举AccountTradeTypeEnum）
	 */
	private Integer trxType;
	public String getRequestNo() {
		return requestNo;
	}
	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getCurrentAmount() {
		return currentAmount;
	}
	public void setCurrentAmount(Double currentAmount) {
		this.currentAmount = currentAmount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getAccountFrozenHistoryType() {
		return accountFrozenHistoryType;
	}
	public void setAccountFrozenHistoryType(Integer accountFrozenHistoryType) {
		this.accountFrozenHistoryType = accountFrozenHistoryType;
	}
	public Integer getTrxType() {
		return trxType;
	}
	public void setTrxType(Integer trxType) {
		this.trxType = trxType;
	}

	
}
