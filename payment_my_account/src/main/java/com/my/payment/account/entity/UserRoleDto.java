package com.my.payment.account.entity;

public class UserRoleDto {
	private UserDto user;
	private RoleDto role;
	
	
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	
	public RoleDto getRole() {
		return role;
	}
	public void setRole(RoleDto role) {
		this.role = role;
	}
}
