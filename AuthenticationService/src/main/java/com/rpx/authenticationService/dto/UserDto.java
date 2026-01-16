package com.rpx.authenticationService.dto;

public record UserDto(Long id, String name, String userName, String password, String email, String mobileNumber,
		Long roleId) {
}
