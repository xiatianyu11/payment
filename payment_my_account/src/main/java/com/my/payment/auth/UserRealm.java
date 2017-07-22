package com.my.payment.auth;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.my.payment.account.entity.AclDto;
import com.my.payment.account.entity.RoleDto;
import com.my.payment.account.entity.UserDto;
import com.my.payment.base.factory.SpringContextUtil;
import com.my.payment.member.dao.AclDao;
import com.my.payment.member.dao.ControllerResourcesDao;
import com.my.payment.member.dao.UserDao;

public class UserRealm extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		UserDto user = ((UserDto)principals.getPrimaryPrincipal());
		long uid = user.getId();
		System.out.println(user.getId()+","+user.getNickname());
		
		UserDao userDao = (UserDao)SpringContextUtil.getBean("userDao");
		List<RoleDto> roles = userDao.listRolesOfUser(uid);
		Set<String> rolePermissions = new HashSet<String>();
		for(RoleDto role : roles){
			rolePermissions.add((role.getId()).toString());
		}
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(rolePermissions);
		return info;
	}
	

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UserDao userDao = (UserDao)SpringContextUtil.getBean("userDao");
		String username = token.getPrincipal().toString();
		String password = new String((char[])token.getCredentials());
		UserDto user = userDao.login(username, password);
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		info.setCredentialsSalt(ByteSource.Util.bytes(user.getUsername()));
		return info;
	}


	@Override
	protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		//System.out.println("清除授权的缓存");
		Cache c = this.getAuthorizationCache();
		Set<Object> keys = c.keys();
		for(Object o:keys) {
			System.out.println("授权缓存:"+o+"-----"+c.get(o)+"----------");
		}
		
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		//System.out.println("清除认证的缓存");
		Cache c = this.getAuthenticationCache();
		Set<Object> keys = c.keys();
		for(Object o:keys) {
			System.out.println("认证缓存:"+o+"----------"+c.get(o)+"----------");
		}
		UserDto user = ((UserDto)principals.getPrimaryPrincipal());
		SimplePrincipalCollection spc = new SimplePrincipalCollection(user.getUsername(), getName());
		super.clearCachedAuthenticationInfo(spc);
	}
	
	

}
