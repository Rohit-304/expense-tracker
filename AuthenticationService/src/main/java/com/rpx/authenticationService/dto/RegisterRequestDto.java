package com.rpx.authenticationService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class RegisterRequestDto {

	@NotBlank(message = "Name is required")
	@Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
	private String name;

	@NotBlank(message = "Username is required")
	@Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters")
	private String userName;

	@NotBlank(message = "Password is required")
	@Size(min = 8, max = 20, message = "Password must be at least 8 characters")
	private String password;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;

	@NotBlank(message = "Mobile number is required")
	@Pattern(regexp = "^[6-9][0-9]{9}$", message = "Mobile number must be a valid 10-digit Indian number")
	private String mobileNumber;

	@NotNull(message = "Role ID is required")
	@Positive(message = "Role ID must be a positive number")
	private Long roleId;

	public RegisterRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegisterRequestDto(String name, String userName, String password, String email, String mobileNumber,
			Long roleId) {
		super();
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
