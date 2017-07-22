package com.my.payment.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;

import com.my.payment.account.entity.AclDto;
import com.my.payment.account.entity.ControllerResourcesDto;
import com.my.payment.base.factory.SpringContextUtil;
import com.my.payment.member.dao.AclDao;
import com.my.payment.member.dao.ControllerResourcesDao;

public class AclRolePermissionResolver implements RolePermissionResolver {

	@Override
	public Collection<Permission> resolvePermissionsInRole(String roleString) {
		// TODO Auto-generated method stub
		ControllerResourcesDao controllerResourcesDao = (ControllerResourcesDao)SpringContextUtil.getBean("controllerResourcesDao");
		AclDao aclDao = (AclDao)SpringContextUtil.getBean("aclDao");
		AclDto dto = new AclDto();
		dto.setRoleId(new Long(roleString));
		List<AclDto> aclList = aclDao.getAll(dto);
		if(aclList != null ){
			List<Permission> plist = new ArrayList<Permission>();
			for(AclDto acl : aclList){
				ControllerResourcesDto csDto = new ControllerResourcesDto();
				csDto.setId(acl.getResourceId());;
				ControllerResourcesDto resource = controllerResourcesDao.getRow(csDto);
				plist.add(new AclPermission("+" + resource.getClassName() + "+" + acl.getAclState()));
			}
			return plist;
		}
		return null;
	}

}
