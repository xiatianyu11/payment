package com.my.payment.account.entity;

import com.my.payment.base.annotations.EntityPK;
import com.my.payment.base.dto.impl.BaseDtoImpl;

@EntityPK(Pk = "id", tableName = "t_role")
public class RoleDto extends BaseDtoImpl{
	private static final long serialVersionUID = 1L;
	private String name;
	private String sn;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
}
