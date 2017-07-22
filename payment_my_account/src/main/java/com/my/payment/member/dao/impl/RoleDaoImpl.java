package com.my.payment.member.dao.impl;

import org.springframework.stereotype.Repository;

import com.my.payment.account.entity.RoleDto;
import com.my.payment.base.dao.impl.BaseDaoImpl;
import com.my.payment.member.dao.RoleDao;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<RoleDto> implements RoleDao {


}
