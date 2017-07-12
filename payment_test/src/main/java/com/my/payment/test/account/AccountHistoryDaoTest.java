package com.my.payment.test.account;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.my.payment.account.dao.AccountHistoryDao;
import com.my.payment.account.entity.AccountHistoryDto;
import com.my.payment.account.entity.DailyCollectAccountHistoryVo;

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
	
	@Test
	public void testListDailyCollectAccountHistoryVo(){
		List<DailyCollectAccountHistoryVo> dtoList = accountHistoryDao.listDailyCollectAccountHistoryVo("80080011000005120101", "2014-10-11", 10000, 123);
		Assert.assertNotNull(dtoList);
		
	}
	
	@Test
	public void testListDailyCollectAccountHistoryVo_t0(){
		List<DailyCollectAccountHistoryVo> dtoList = accountHistoryDao.listDailyCollectAccountHistoryVo_t0("80080011000005120101", "10042014101110003011");
		Assert.assertNotNull(dtoList);
		
	}
	
	@Test
	public void testUpdateCompleteSettTo100(){
		accountHistoryDao.updateCompleteSettTo100("80080011000005120101", "2014-10-12", 100);
		
	}
	
	@Test
	public void testUpdateCompleteSettTo100_t0(){
		accountHistoryDao.updateCompleteSettTo100_t0("80080011000005120101", "10042014101110003011");
		
	}
	
	@Test
	public void testUpdateCompleteSettTo100LastId(){
		accountHistoryDao.updateCompleteSettTo100LastId("80080011000005120101", new Long(252));
		
	}

}
