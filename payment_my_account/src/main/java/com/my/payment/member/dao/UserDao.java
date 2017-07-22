package com.my.payment.member.dao;

import java.util.List;

import com.my.payment.account.entity.RoleDto;
import com.my.payment.account.entity.UserDto;
import com.my.payment.base.dao.BaseDao;

public interface UserDao extends BaseDao<UserDto>{
	
	public List<RoleDto> listRolesOfUser(Long userId);
	
	public UserDto login(String userName, String pwd);

}
