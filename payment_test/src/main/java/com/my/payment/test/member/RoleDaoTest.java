package com.my.payment.test.member;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.my.payment.account.entity.RoleDto;
import com.my.payment.member.dao.RoleDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:conf/spring*.xml")
public class RoleDaoTest {
	
	@Autowired
	private RoleDao roleDao;
	
	@Test
	public void testExist(){
		
		//System.out.println(System.getProperty("test.conf"));
		List<RoleDto> list = roleDao.getAll(new RoleDto());
		Assert.assertTrue(list.size() > 0);
	}

}
