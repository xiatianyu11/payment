package com.my.payment.test.member;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.my.payment.account.constant.Constant;
import com.my.payment.account.dao.AccountDao;
import com.my.payment.account.entity.AclDto;
import com.my.payment.account.entity.RoleDto;
import com.my.payment.account.entity.UserDto;
import com.my.payment.member.dao.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:conf/spring*.xml")
public class UserDaoTest {
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void testBuildAccountNo(){
		
		//System.out.println(System.getProperty("test.conf"));
		List<UserDto> list = userDao.getAll(new UserDto());
		Assert.assertTrue(list.size() > 0);
	}
	
	@Test
	public void testList(){
		
		//System.out.println(System.getProperty("test.conf"));
		List<RoleDto> list = userDao.listRolesOfUser(new Long(5));
		Assert.assertTrue(list.size() > 0);
	}

}
