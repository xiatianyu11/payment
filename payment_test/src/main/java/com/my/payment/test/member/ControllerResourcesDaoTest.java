package com.my.payment.test.member;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.my.payment.account.entity.ControllerResourcesDto;
import com.my.payment.member.dao.ControllerResourcesDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:conf/spring*.xml")
public class ControllerResourcesDaoTest {
	
	@Autowired
	private ControllerResourcesDao controllerResourcesDao;
	
	@Test
	public void testExist(){
		
		//System.out.println(System.getProperty("test.conf"));
		ControllerResourcesDto dto = new ControllerResourcesDto();
		dto.setId((long) 1);
		dto = controllerResourcesDao.getRow(dto);
		Assert.assertNotNull(dto);
	}

}
