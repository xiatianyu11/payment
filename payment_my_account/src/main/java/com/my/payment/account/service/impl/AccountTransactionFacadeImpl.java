package com.my.payment.account.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.my.payment.account.constant.PublicStatus;
import com.my.payment.account.dao.AccountDao;
import com.my.payment.account.dao.AccountFrozenHistoryDao;
import com.my.payment.account.dao.AccountHistoryDao;
import com.my.payment.account.entity.AccountDto;
import com.my.payment.account.entity.AccountFrozenHistoryDto;
import com.my.payment.account.entity.AccountHistoryDto;
import com.my.payment.account.entity.AccountTransactionVo;
import com.my.payment.account.enums.AccountFrozenHistoryTypeEnum;
import com.my.payment.account.enums.AccountFundDirectionEnum;
import com.my.payment.account.enums.AccountStatusEnum;
import com.my.payment.account.enums.AccountTradeTypeEnum;
import com.my.payment.account.enums.AccountTypeEnum;
import com.my.payment.account.exception.AccountBizException;
import com.my.payment.account.service.AccountTransactionFacade;
import com.my.payment.base.utils.StringUtil;


/**
 * 账户交易
 * 
 * 
 */
@Service("accountTransactionFacade")
public class AccountTransactionFacadeImpl implements AccountTransactionFacade {

	@Autowired
	private AccountDao accountDao;
	@Autowired
	private AccountHistoryDao accountHistoryDao;
	@Autowired
	private AccountFrozenHistoryDao accountFrozenHistoryDao;

	private static final Log log = LogFactory.getLog(AccountTransactionFacadeImpl.class);
	/**
	 * 账户收/付款(单笔).
	 * 
	 * @param vo
	 *            交易命令参数vo .
	 */
	public void execute(AccountTransactionVo vo) {
		if (StringUtil.isBlank(vo.getUserNo())) {
			return;
		}

		log.info("==>execute");

		if (vo.getAccountFundDirection().equals(AccountFundDirectionEnum.ADD)) {
			this.credit(vo.getUserNo(), vo.getAmount(), vo.getRequestNo(), vo.getTradeType(), vo.getDesc(), vo.getRiskDay(), vo.getFee());
		} else if (vo.getAccountFundDirection().equals(AccountFundDirectionEnum.SUB)) {
			this.debit(vo.getUserNo(), vo.getAmount(), vo.getRequestNo(), vo.getTradeType(), vo.getDesc(), vo.getFee());
		} else if (vo.getAccountFundDirection().equals(AccountFundDirectionEnum.FROZEN)) {
			this.frozen(vo.getUserNo(), vo.getFrezonAmount(), vo.getRequestNo(), vo.getTradeType());
		} else if (vo.getAccountFundDirection().equals(AccountFundDirectionEnum.UNFROZEN)) {
			this.unFrozen(vo.getUserNo(), vo.getUnFrezonAmount(), vo.getRequestNo(), vo.getTradeType());
		}
	}

	/**
	 * 账户收/付款(批量)
	 * 
	 * @param voList
	 *            交易命令参数vo集 .
	 * @throws BizException
	 */
	public void execute(List<AccountTransactionVo> voList) {
		for (AccountTransactionVo vo : voList) {
			this.execute(vo);
		}
	}

	/**
	 * 同一账户批量加款.
	 * 
	 * @param voList
	 *            交易命令参数vo集.
	 */
	public void batchCreditForSameAccount(List<AccountTransactionVo> list) {
		if (list == null || list.isEmpty()) {
			return;
		}

		log.info("==>batchCreditForSameAccount");

		AccountDto account = accountDao.getByUserNo(list.get(0).getUserNo());
		if (account == null) {
			throw AccountBizException.ACCOUNT_NOT_EXIT.newInstance("账户不存在,用户编号{%s}", list.get(0).getUserNo()).print();
		}

		if (account.getStatus() == AccountStatusEnum.INACTIVE.getValue() || account.getStatus() == AccountStatusEnum.CANCELLED.getValue()
				|| account.getStatus() == AccountStatusEnum.INACTIVE_FREEZE_CREDIT.getValue()) {
			throw AccountBizException.ACCOUNT_STATUS_IS_INACTIVE.newInstance("账户状态异常,用户编号{%s},账户状态{%s}", list.get(0).getUserNo(), account.getStatus()).print();
		}

		int isAllowSett = PublicStatus.ACTIVE;

		// 如果accountType是会员，isAllowSett置false
		if (account.getAccountType() == AccountTypeEnum.CUSTOMER.getValue()) {
			isAllowSett = PublicStatus.INACTIVE;
		}

		List<AccountHistoryDto> listHistory = new ArrayList<AccountHistoryDto>();
		for (AccountTransactionVo vo : list) {

			if (!vo.getUserNo().equals(account.getUserNo())) {
				throw AccountBizException.BATCH_CREDIT_FOR_SAME_ACCOUNT_ERROR.print();
			}

			account.credit(vo.getAmount()); // 加款

			AccountHistoryDto accountHistory = new AccountHistoryDto();
			accountHistory.setIsAllowSett(isAllowSett);
			accountHistory.setAmount(vo.getAmount());
			accountHistory.setFee(vo.getFee());
			accountHistory.setBalance(account.getAvailableBalance());
			accountHistory.setRequestNo(vo.getRequestNo());
			accountHistory.setIsCompleteSett(PublicStatus.INACTIVE);
			accountHistory.setRemark(vo.getTradeType().getDesc());
			accountHistory.setFundDirection(AccountFundDirectionEnum.ADD.getValue());
			accountHistory.setAccountNo(account.getAccountNo());
			accountHistory.setTrxType(vo.getTradeType().getValue());
			accountHistory.setRiskDay(vo.getRiskDay());
			listHistory.add(accountHistory);
		}

		accountDao.updatePK(account);
		accountHistoryDao.saveBatch(listHistory);
	}

	/**
	 * 资金冻结.
	 * 
	 * @param userNo
	 *            用户编号.
	 * @param frozenAmount
	 *            冻结金额.
	 * @param requestNo
	 *            请求号.
	 * @param tradeType
	 *            账户交易类型.
	 */
	public void frozen(String userNo, double frozenAmount, String requestNo, AccountTradeTypeEnum tradeType) {

		log.info("==>frozen");
		log.info(String.format("==>userNo:%s, frozenAmount:%s, requestNo:%s, tradeType:%s", userNo, frozenAmount, requestNo, tradeType.name()));

		// if (AmountUtil.greaterThanOrEqualTo(0, frozenAmount)) {
		// throw AccountBizException.ACCOUNT_AMOUNT_ERROR.print();
		// }

		AccountDto account = accountDao.getByUserNo(userNo);
		if (account == null) {
			throw AccountBizException.ACCOUNT_NOT_EXIT.newInstance("账户不存在,用户编号{%s}", userNo).print();
		}

		account.frozen(frozenAmount); // 资金冻结

		// 如果accountType是会员，isAllowSett置false
		int isAllowSett = PublicStatus.ACTIVE;
		if (account.getAccountType() == AccountTypeEnum.CUSTOMER.getValue()) {
			isAllowSett = PublicStatus.INACTIVE;
		}

		// 结算，提现交易类型不允许结算 by chenjianhua
		if (tradeType.equals(AccountTradeTypeEnum.SETTLEMENT) || tradeType.equals(AccountTradeTypeEnum.ATM)) {
			isAllowSett = PublicStatus.INACTIVE;
		}

		AccountFrozenHistoryDto accountFrozenHistory = new AccountFrozenHistoryDto();
		accountFrozenHistory.setAmount(frozenAmount);
		accountFrozenHistory.setCurrentAmount(account.getUnBalance());
		accountFrozenHistory.setRequestNo(requestNo);
		accountFrozenHistory.setRemark(tradeType.getDesc()+"资金冻结");
		accountFrozenHistory.setAccountFrozenHistoryType(AccountFrozenHistoryTypeEnum.FROZEN.getValue());
		accountFrozenHistory.setAccountNo(account.getAccountNo());
		accountFrozenHistory.setTrxType(tradeType.getValue());

		AccountHistoryDto accountHistoryEntity = new AccountHistoryDto();
		accountHistoryEntity.setIsAllowSett(isAllowSett);
		accountHistoryEntity.setAmount(frozenAmount);
		accountHistoryEntity.setBalance(account.getAvailableBalance());
		accountHistoryEntity.setRequestNo(requestNo);
		accountHistoryEntity.setIsCompleteSett(PublicStatus.INACTIVE);
		accountHistoryEntity.setRemark(tradeType.getDesc()+"资金冻结");
		accountHistoryEntity.setFundDirection(AccountFundDirectionEnum.SUB.getValue());
		accountHistoryEntity.setAccountNo(account.getAccountNo());
		accountHistoryEntity.setTrxType(tradeType.getValue());

		accountDao.updatePK(account);
		accountHistoryDao.save(accountHistoryEntity);
		accountFrozenHistoryDao.save(accountFrozenHistory);

		log.info("==>frozen<==");
	}

	/**
	 * 资金解冻.
	 * 
	 * @param userNo
	 *            用户编号.
	 * @param unFrozenAmount
	 *            解冻金额.
	 * @param requestNo
	 *            请求号.
	 * @param tradeType
	 *            账户交易类型.
	 */
	public void unFrozen(String userNo, double unFrozenAmount, String requestNo, AccountTradeTypeEnum tradeType) {

		log.info("==>unFrozen");
		log.info(String.format("==>userNo:%s, unFrozenAmount:%s, requestNo:%s, tradeType:%s", userNo, unFrozenAmount, requestNo, tradeType.name()));

		// if (AmountUtil.greaterThanOrEqualTo(0, unFrozenAmount)) {
		// throw AccountBizException.ACCOUNT_AMOUNT_ERROR.print();
		// }

		AccountDto account = accountDao.getByUserNo(userNo);
		if (account == null) {
			throw AccountBizException.ACCOUNT_NOT_EXIT.newInstance("账户不存在,用户编号{%s}", userNo).print();
		}

		account.unFrozen(unFrozenAmount); // 资金解冻

		// 如果accountType是会员，isAllowSett置false
		int isAllowSett = PublicStatus.ACTIVE;
		if (account.getAccountType() == AccountTypeEnum.CUSTOMER.getValue()) {
			isAllowSett = PublicStatus.INACTIVE;
		}

		// 结算，提现交易类型不允许结算 by chenjianhua
		if (tradeType.equals(AccountTradeTypeEnum.SETTLEMENT) || tradeType.equals(AccountTradeTypeEnum.ATM)) {
			isAllowSett = PublicStatus.INACTIVE;
		}

		AccountFrozenHistoryDto accountFrozenHistory = new AccountFrozenHistoryDto();
		accountFrozenHistory.setAmount(unFrozenAmount);
		accountFrozenHistory.setCurrentAmount(account.getUnBalance());
		accountFrozenHistory.setRequestNo(requestNo);
		accountFrozenHistory.setRemark(tradeType.getDesc()+"资金解冻");
		accountFrozenHistory.setAccountFrozenHistoryType(AccountFrozenHistoryTypeEnum.UNFROZEN.getValue());
		accountFrozenHistory.setAccountNo(account.getAccountNo());
		accountFrozenHistory.setTrxType(tradeType.getValue());

		AccountHistoryDto accountHistoryEntity = new AccountHistoryDto();
		accountHistoryEntity.setIsAllowSett(isAllowSett);
		accountHistoryEntity.setAmount(unFrozenAmount);
		accountHistoryEntity.setBalance(account.getAvailableBalance());
		accountHistoryEntity.setRequestNo(requestNo);
		accountHistoryEntity.setIsCompleteSett(PublicStatus.INACTIVE);
		accountHistoryEntity.setRemark(tradeType.getDesc()+"资金解冻");
		accountHistoryEntity.setFee(0D);
		accountHistoryEntity.setFundDirection(AccountFundDirectionEnum.ADD.getValue());
		accountHistoryEntity.setAccountNo(account.getAccountNo());
		accountHistoryEntity.setTrxType(tradeType.getValue());

		accountDao.updatePK(account);
		accountFrozenHistoryDao.save(accountFrozenHistory);
		accountHistoryDao.save(accountHistoryEntity);

		log.info("==>unFrozen<==");
	}


	/**
	 * 资金解冻并减款.
	 * 
	 * @param userNo
	 *            用户编号.
	 * @param unFrozenAmount
	 *            解冻金额.
	 * @param requestNo
	 *            请求号.
	 * @param tradeType
	 *            账户交易类型.
	 * @param fee
	 *            手续费
	 */
	public void unfrozen_debit(String userNo, double amount, String requestNo, AccountTradeTypeEnum tradeType, double fee) {
		this.unFrozen(userNo, amount, requestNo, tradeType);
		this.debit(userNo, amount, requestNo, tradeType, tradeType.getDesc(), fee);
	}
	
	
	public String credit(String userNo, double amount, String requestNo, AccountTradeTypeEnum tradeType, String remark, Integer riskDay, double fee) {

		log.info("==>credit");

		AccountDto account = accountDao.getByUserNo(userNo);
		if (account == null) {
			throw AccountBizException.ACCOUNT_NOT_EXIT.newInstance("账户不存在,用户编号{%s}", userNo).print();
		}

		account.credit(amount); // 加款

		int isAllowSett = PublicStatus.ACTIVE;

		// 如果accountType是会员，isAllowSett置false
		if (account.getAccountType() == AccountTypeEnum.CUSTOMER.getValue()) {
			isAllowSett = PublicStatus.INACTIVE;
		}

		AccountHistoryDto accountHistoryEntity = new AccountHistoryDto();
		accountHistoryEntity.setIsAllowSett(isAllowSett);
		accountHistoryEntity.setAmount(amount);
		accountHistoryEntity.setBalance(account.getAvailableBalance());
		accountHistoryEntity.setRequestNo(requestNo);
		accountHistoryEntity.setIsCompleteSett(PublicStatus.INACTIVE);
		accountHistoryEntity.setRemark(remark);
		accountHistoryEntity.setFee(fee);
		accountHistoryEntity.setFundDirection(AccountFundDirectionEnum.ADD.getValue());
		accountHistoryEntity.setAccountNo(account.getAccountNo());
		accountHistoryEntity.setTrxType(tradeType.getValue());
		accountHistoryEntity.setRiskDay(riskDay);

		accountHistoryDao.save(accountHistoryEntity);
		accountDao.updatePK(account);

		log.info("==>credit<==");

		return account.getAccountTitleNo();

	}
	
	private String debit(String userNo, double amount, String requestNo, AccountTradeTypeEnum tradeType, String remark, double fee) {

		log.info("==>debit");
		log.info(String.format("==>userNo:%s, amount:%s, requestNo:%s, tradeType:%s, remark:%s", userNo, amount, requestNo, tradeType.name(), remark));

		AccountDto account = accountDao.getByUserNo(userNo);
		if (account == null) {
			throw AccountBizException.ACCOUNT_NOT_EXIT.newInstance("账户不存在,用户编号{%s}", userNo).print();
		}

		account.debit(amount); // 减款

		// 如果accountType是会员，isAllowSett置false
		int isAllowSett = PublicStatus.ACTIVE;
		if (account.getAccountType() == AccountTypeEnum.CUSTOMER.getValue()) {
			isAllowSett = PublicStatus.INACTIVE;
		}

		// 结算，提现交易类型不允许结算 by chenjianhua
		if (tradeType.equals(AccountTradeTypeEnum.SETTLEMENT) || tradeType.equals(AccountTradeTypeEnum.ATM)) {
			isAllowSett = PublicStatus.INACTIVE;
		}

		AccountHistoryDto accountHistoryEntity = new AccountHistoryDto();
		accountHistoryEntity.setIsAllowSett(isAllowSett);
		accountHistoryEntity.setAmount(amount);
		accountHistoryEntity.setFee(fee);
		accountHistoryEntity.setBalance(account.getAvailableBalance());
		accountHistoryEntity.setRequestNo(requestNo);
		accountHistoryEntity.setIsCompleteSett(PublicStatus.INACTIVE);
		accountHistoryEntity.setRemark(remark);
		accountHistoryEntity.setFundDirection(AccountFundDirectionEnum.SUB.getValue());
		accountHistoryEntity.setAccountNo(account.getAccountNo());
		accountHistoryEntity.setTrxType(tradeType.getValue());

		accountHistoryDao.save(accountHistoryEntity);
		accountDao.updatePK(account);

		log.info("==>debit<==");

		return account.getAccountTitleNo();
	}
}
