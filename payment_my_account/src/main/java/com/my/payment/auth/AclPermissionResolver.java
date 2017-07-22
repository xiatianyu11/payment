package com.my.payment.auth;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

public class AclPermissionResolver implements PermissionResolver {

	@Override
	public Permission resolvePermission(String permissionString) {
		// TODO Auto-generated method stub
		if (permissionString.startsWith("+")) {
			return new AclPermission(permissionString);
		}
		return new WildcardPermission(permissionString);
	}

}
