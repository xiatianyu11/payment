package com.my.payment.account.entity;

import com.my.payment.base.annotations.EntityPK;
import com.my.payment.base.dto.impl.BaseDtoImpl;

@EntityPK(Pk = "id", tableName = "t_acl")
public class AclDto extends BaseDtoImpl{
	private static final long serialVersionUID = 1L;
	private String resourceType;
	private Long roleId;
	private Long resourceId;
	private int aclState;
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public int getAclState() {
		return aclState;
	}
	public void setAclState(int aclState) {
		this.aclState = aclState;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getResourceId() {
		return resourceId;
	}
	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
	
	

}
