package com.my.payment.account.service.impl;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.payment.account.dao.AccountDao;
import com.my.payment.account.dao.AccountFrozenRecordDao;
import com.my.payment.account.entity.AccountDto;
import com.my.payment.account.entity.AccountFrozenRecordDto;
import com.my.payment.account.enums.AccountInitiatorEnum;
import com.my.payment.account.enums.AccountOperationTypeEnum;
import com.my.payment.account.enums.AccountStatusEnum;
import com.my.payment.account.enums.AccountTypeEnum;
import com.my.payment.account.exception.AccountBizException;
import com.my.payment.account.service.AccountManagementFacade;
import com.my.payment.base.utils.StringUtil;

@Service("accountManagementFacade")
public class AccountManagementFacadeImpl implements AccountManagementFacade{
	private static final Log log = LogFactory.getLog(AccountManagementFacadeImpl.class);
	
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private AccountFrozenRecordDao accountFrozenRecordDao;

	@Override
	public long reBindUserNo(String accountNo, String userNo)
			throws AccountBizException {
		AccountDto account = accountDao.getByAccountNo(accountNo);
		if (account == null) {
			throw AccountBizException.ACCOUNT_NOT_EXIT.print();
		}

		account.setUserNo(userNo);
		account.setLastTime(new Date());

		long v = accountDao.updatePK(account);

		log.info("==>changeUserNo, accountNo:" + accountNo + ", userNo:" + userNo);

		return v;
	}

	@Override
	public String buildAccountNo(AccountTypeEnum accountType)
			throws AccountBizException {
		String accountNo = accountDao.buildAccountNo(StringUtil.leftPadWithBytes(String.valueOf(accountType.getValue()), 3, '0', "UTF-8"));

		log.info("==>buildAccountNo:" + accountNo);

		return accountNo;
	}

	@Override
	public long createAccount(String userNo, String accountNo, int accountType)
			throws AccountBizException {

		// 隶属叶子科目编号
		
		AccountTypeEnum accountTypeEnum = AccountTypeEnum.getEnum(accountType);
		String titleNo = accountTypeEnum.getTitleNo();

		AccountDto account = new AccountDto();
		account.setUserNo(userNo);
		account.setAccountType(accountType);
		account.setAccountNo(accountNo);
		account.setStatus(AccountStatusEnum.ACTIVE.getValue());
		account.setAccountTitleNo(titleNo);
		accountDao.save(account);
		return 0;
	}

	@Override
	public long createPrivateAccount(String userNo, String accountNo,
			Double balance, Double securityMoney) throws AccountBizException {
		AccountDto account = new AccountDto();
		account.setUserNo(userNo);
		account.setAccountNo(accountNo);
		account.setAccountType(AccountTypeEnum.PRIVATE.getValue());
		account.setStatus(AccountStatusEnum.ACTIVE.getValue());
		account.setAccountTitleNo(AccountTypeEnum.PRIVATE.getTitleNo());
		account.setBalance(balance);
		account.setSecurityMoney(securityMoney);

		accountDao.save(account);
		return 0;
	}

	@Override
	public void changeAccountStatus(String userNo,
			AccountOperationTypeEnum operationType,
			AccountInitiatorEnum initiator, String desc)
			throws AccountBizException {
		AccountDto account = accountDao.getByUserNo(userNo);
		if (account == null) {
			throw AccountBizException.ACCOUNT_NOT_EXIT.print();
		}

		if (operationType.equals(AccountOperationTypeEnum.FREEZE_DEBIT)) {
			account.setStatus(AccountStatusEnum.INACTIVE_FREEZE_DEBIT.getValue());
		} else if (operationType.equals(AccountOperationTypeEnum.UNFREEZE_DEBIT)) {
			account.setStatus(AccountStatusEnum.ACTIVE.getValue());
		} else if (operationType.equals(AccountOperationTypeEnum.FREEZE_CREDIT)) {
			account.setStatus(AccountStatusEnum.INACTIVE_FREEZE_CREDIT.getValue());
		} else if (operationType.equals(AccountOperationTypeEnum.UNFREEZE_CREDIT)) {
			account.setStatus(AccountStatusEnum.ACTIVE.getValue());
		} else if (operationType.equals(AccountOperationTypeEnum.FREEZE_ACCOUNT)) {
			account.setStatus(AccountStatusEnum.INACTIVE.getValue());
		} else if (operationType.equals(AccountOperationTypeEnum.UNFREEZE_ACCOUNT)) {
			account.setStatus(AccountStatusEnum.ACTIVE.getValue());
		} else if (operationType.equals(AccountOperationTypeEnum.CREATE_ACCOUNT)) {
			account.setStatus(AccountStatusEnum.ACTIVE.getValue());
		} else if (operationType.equals(AccountOperationTypeEnum.FREEZE_FUND)) {
			account.setStatus(AccountStatusEnum.INACTIVE.getValue());
		} else if (operationType.equals(AccountOperationTypeEnum.UNFREEZE_FUND)) {
			account.setStatus(AccountStatusEnum.ACTIVE.getValue());
		} else if (operationType.equals(AccountOperationTypeEnum.CANCEL_ACCOUNT)) {
			account.setStatus(AccountStatusEnum.CANCELLED.getValue());
		}
		account.setLastTime(new Date());
		accountDao.updatePK(account);

		AccountFrozenRecordDto accountFrozenRecord = new AccountFrozenRecordDto();
		accountFrozenRecord.setLastTime(new Date());
		accountFrozenRecord.setAccountNo(account.getAccountNo());
		accountFrozenRecord.setRemark(desc);
		accountFrozenRecord.setInitiator(initiator.getValue());
		accountFrozenRecord.setOperationType(operationType.getValue());

		accountFrozenRecordDao.save(accountFrozenRecord);
		
	}

}
