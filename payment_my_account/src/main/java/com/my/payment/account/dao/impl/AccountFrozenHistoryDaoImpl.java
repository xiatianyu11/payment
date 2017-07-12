package com.my.payment.account.dao.impl;

import org.springframework.stereotype.Repository;

import com.my.payment.account.dao.AccountFrozenHistoryDao;
import com.my.payment.account.entity.AccountFrozenHistoryDto;
import com.my.payment.base.dao.impl.BaseDaoImpl;

@Repository("accountFrozenHistoryDao")
public class AccountFrozenHistoryDaoImpl  extends BaseDaoImpl<AccountFrozenHistoryDto> implements AccountFrozenHistoryDao {

}
