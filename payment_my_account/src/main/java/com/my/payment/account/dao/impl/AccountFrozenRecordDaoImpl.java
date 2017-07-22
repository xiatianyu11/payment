package com.my.payment.account.dao.impl;

import org.springframework.stereotype.Repository;

import com.my.payment.account.dao.AccountFrozenRecordDao;
import com.my.payment.account.entity.AccountFrozenRecordDto;
import com.my.payment.base.dao.impl.BaseDaoImpl;

@Repository("accountFrozenRecordDao")
public class AccountFrozenRecordDaoImpl extends BaseDaoImpl<AccountFrozenRecordDto> implements AccountFrozenRecordDao {

}
