package com.my.payment.test.account;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.my.payment.account.dao.AccountDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:conf/spring*.xml")
public class AccountDaoTest {
	
	@Autowired
	private AccountDao accountDao;
	
	@Test
	public void testBuildAccountNo(){
		
		//System.out.println(System.getProperty("test.conf"));
		String accountNo = accountDao.buildAccountNo("TESt");
		System.out.println(accountNo);
		Assert.assertNotNull(accountNo);
	}

}
