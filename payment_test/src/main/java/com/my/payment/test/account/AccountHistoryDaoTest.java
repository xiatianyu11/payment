package com.my.payment.test.account;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.my.payment.account.dao.AccountDao;
import com.my.payment.account.dao.AccountHistoryDao;
import com.my.payment.account.entity.AccountHistoryDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:conf/spring*.xml")
public class AccountHistoryDaoTest {
	
	@Autowired
	private AccountHistoryDao accountHistoryDao;
	
	@Test
	public void testGetByAccountNo_requestNo_trxType(){
		AccountHistoryDto dto = accountHistoryDao.getByAccountNo_requestNo_trxType("80080011000005120101", "10042014101110003011", 1001);
		Assert.assertNotNull(dto);
	}

}
