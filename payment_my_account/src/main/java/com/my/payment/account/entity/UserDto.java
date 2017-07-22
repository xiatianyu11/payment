package com.my.payment.account.entity;


import com.my.payment.base.annotations.EntityPK;
import com.my.payment.base.dto.impl.BaseDtoImpl;

@EntityPK(Pk = "id", tableName = "t_user")
public class UserDto extends BaseDtoImpl{
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String nickname;
	private int status;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
