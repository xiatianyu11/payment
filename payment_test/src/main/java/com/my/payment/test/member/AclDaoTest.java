package com.my.payment.test.member;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.my.payment.account.constant.Constant;
import com.my.payment.account.entity.AclDto;
import com.my.payment.account.entity.RoleDto;
import com.my.payment.member.dao.AclDao;
import com.my.payment.member.dao.RoleDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:conf/spring*.xml")
public class AclDaoTest {
	
	@Autowired
	private AclDao aclDao;
	
	@Test
	public void testExist(){
		
		//System.out.println(System.getProperty("test.conf"));
		AclDto dto = new AclDto();
		dto.setResourceType(Constant.RESOURCE_TYPE_CTRL);
		dto.setRoleId(new Long(1));
		List<AclDto> list = aclDao.getAll(dto);
		Assert.assertTrue(list.size() > 0);
	}
	



}
