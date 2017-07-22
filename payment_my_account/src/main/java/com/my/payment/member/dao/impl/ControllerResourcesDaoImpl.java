package com.my.payment.member.dao.impl;

import org.springframework.stereotype.Repository;

import com.my.payment.account.entity.ControllerResourcesDto;
import com.my.payment.base.dao.impl.BaseDaoImpl;
import com.my.payment.member.dao.ControllerResourcesDao;

@Repository("controllerResoucesDao")
public class ControllerResourcesDaoImpl extends BaseDaoImpl<ControllerResourcesDto> implements ControllerResourcesDao {


}
