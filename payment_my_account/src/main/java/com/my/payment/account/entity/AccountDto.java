package com.my.payment.account.entity;

import java.util.Date;

import com.my.payment.account.enums.AccountStatusEnum;
import com.my.payment.account.exception.AccountBizException;
import com.my.payment.base.annotations.EntityPK;
import com.my.payment.base.dto.impl.BaseDtoImpl;
import com.my.payment.base.utils.AmountUtil;

@EntityPK(Pk = "id", tableName = "TBL_ACCOUNT")
public class AccountDto extends BaseDtoImpl{
	
	/**
	 * 账户编号
	 */
	private String accountNo;
	/**
	 * 用户编号
	 */
	private String userNo;
	/**
	 * 账户状态
	 */
	private Integer status;
	/**
	 * 账户余额
	 */
	private Double balance = 0D;
	/**
	 * 不可用余额
	 */
	private Double unBalance = 0D;
	/**
	 * 保证金
	 */
	private Double securityMoney = 0D;
	/**
	 * 账户类型
	 */
	private Integer accountType;
	/**
	 * 最后更新时间
	 */
	private Date lastTime = new Date();
	/**
	 * 可结算金额
	 */
	private Double availableSettAmount = 0D;
	/**
	 * 会计科目代码
	 */
	private String accountTitleNo;
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Double getUnBalance() {
		return unBalance;
	}
	public void setUnBalance(Double unBalance) {
		this.unBalance = unBalance;
	}
	public Double getSecurityMoney() {
		return securityMoney;
	}
	public void setSecurityMoney(Double securityMoney) {
		this.securityMoney = securityMoney;
	}
	public Integer getAccountType() {
		return accountType;
	}
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public Double getAvailableSettAmount() {
		return availableSettAmount;
	}
	public void setAvailableSettAmount(Double availableSettAmount) {
		this.availableSettAmount = availableSettAmount;
	}
	public String getAccountTitleNo() {
		return accountTitleNo;
	}
	public void setAccountTitleNo(String accountTitleNo) {
		this.accountTitleNo = accountTitleNo;
	}
	
	/**
	 * 获取可用余额
	 * 
	 * @return
	 */
	public Double getAvailableBalance() {
		return AmountUtil.sub(this.balance, this.unBalance);
	}
	
	/**
	 * 资金冻结
	 * 
	 * @param frozenAmount
	 */
	public void frozen(Double frozenAmount) {
		if (!this.availableBalanceIsEnough(frozenAmount)) {
			throw AccountBizException.ACCOUNT_AVAILABLEBALANCE_IS_NOT_ENOUGH.print();
		}
		this.lastTime = new Date();
		this.setUnBalance(AmountUtil.add(this.unBalance, frozenAmount));
	}

	
	/**
	 * 资金解冻
	 * 
	 * @param frozenAmount
	 */
	public void unFrozen(Double frozenAmount) {
		if (AmountUtil.bigger(frozenAmount, this.unBalance)) {
			throw AccountBizException.ACCOUNT_UN_FROZEN_AMOUNT_OUTLIMIT.print();
		}
		this.lastTime = new Date();
		this.setUnBalance(AmountUtil.sub(this.unBalance, frozenAmount));
	}
	
	/**
	 * 存入
	 * 
	 * @param amount
	 */
	public void credit(Double amount) {
		if (this.status.intValue() == AccountStatusEnum.INACTIVE.getValue()
				|| this.status.intValue() == AccountStatusEnum.CANCELLED.getValue()
				|| this.status.intValue() == AccountStatusEnum.INACTIVE_FREEZE_CREDIT.getValue()) {
			throw AccountBizException.ACCOUNT_STATUS_IS_INACTIVE.newInstance("账户状态异常,用户编号{%s},账户状态{%s}", userNo, this.status.intValue())
					.print();
		}
		this.lastTime = new Date();
		this.setBalance(AmountUtil.add(this.balance, amount));
	}
	
	
	/**
	 * 支出
	 * 
	 * @param amount
	 */
	public void debit(Double amount) {
		if (this.status.intValue() == AccountStatusEnum.INACTIVE.getValue()
				|| this.status.intValue() == AccountStatusEnum.CANCELLED.getValue()
				|| this.status.intValue() == AccountStatusEnum.INACTIVE_FREEZE_DEBIT.getValue()) {
			throw AccountBizException.ACCOUNT_STATUS_IS_INACTIVE.newInstance("账户状态异常,用户编号{%s},账户状态{%s}", userNo, this.status.intValue())
					.print();
		}

		if (!this.availableBalanceIsEnough(amount)) {
			throw AccountBizException.ACCOUNT_AVAILABLEBALANCE_IS_NOT_ENOUGH.print();
		}
		this.lastTime = new Date();
		this.setBalance(AmountUtil.sub(this.balance, amount));
	}
	
	/**
	 * 验证可用余额是否足够
	 * 
	 * @param amount
	 * @return
	 */
	public boolean availableBalanceIsEnough(double amount) {
		if (AmountUtil.greaterThanOrEqualTo(this.getAvailableBalance(), amount)) {
			return true;
		} else {
			return false;
		}
	}


}
