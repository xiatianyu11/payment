package com.my.payment.member.dao.impl;

import org.springframework.stereotype.Repository;

import com.my.payment.account.entity.AclDto;
import com.my.payment.base.dao.impl.BaseDaoImpl;
import com.my.payment.member.dao.AclDao;

@Repository("aclDao")
public class AclDaoImpl extends BaseDaoImpl<AclDto> implements AclDao {


}
