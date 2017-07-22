package com.my.payment.member.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.my.payment.account.entity.RoleDto;
import com.my.payment.account.entity.UserDto;
import com.my.payment.base.dao.impl.BaseDaoImpl;
import com.my.payment.member.dao.UserDao;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<UserDto> implements UserDao {
	
	public List<RoleDto> listRolesOfUser(Long userId){
		return this.getSqlSession().selectList("User.listRolesOfUser", userId);
	}

	@Override
	public UserDto login(String userName, String pwd) {
		// TODO Auto-generated method stub
		UserDto dto = new UserDto();
		dto.setUsername(userName);
		dto.setPassword(pwd);
		return this.getRow(dto);
	}

}
