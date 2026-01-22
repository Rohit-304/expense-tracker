package com.rpx.authentication.dto;

public class LoginResponseDto {

	private String userName;

	private String token;

	private Long roleId;

	public LoginResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginResponseDto(String userName, String token, Long roleId) {
		super();
		this.userName = userName;
		this.token = token;
		this.roleId = roleId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
