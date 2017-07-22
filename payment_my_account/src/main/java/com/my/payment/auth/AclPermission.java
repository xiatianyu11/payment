package com.my.payment.auth;

import org.apache.shiro.authz.Permission;

public class AclPermission implements Permission {
	
	private String resourceId;
	private int aclState;
	
	
	/**
	 * 
	 * @param permissionString demo +org.tianyu.HelloController+13(1011)
	 */
	public AclPermission(String permissionString) {
		super();
		String[] array = permissionString.split("\\+");
		
		this.resourceId = array[1];
		this.aclState = Integer.parseInt(array[2]);
	}



	@Override
	public boolean implies(Permission p) {
		// TODO Auto-generated method stub
		if(!(p instanceof AclPermission)) { 
			return false;
		}
		AclPermission other = (AclPermission) p;
		return this.hasPermission(other.getResourceId(), other.getAclState());
	}



	public String getResourceId() {
		return resourceId;
	}


	public int getAclState() {
		return aclState;
	}

	private boolean hasPermission(String compareResourceId, int index){
		if(this.resourceId.equalsIgnoreCase(compareResourceId)){
			return this.hasPermission(index);
		}
		return false;
	}

	private boolean hasPermission(int index){
		int tmp = 1;
		tmp = 1 << index; 
		int num = this.aclState & tmp;
    	return num > 0;
	}

}
