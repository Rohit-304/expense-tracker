package com.rpx.authenticationService.util;

import org.springframework.stereotype.Component;

import com.rpx.authenticationService.dto.LoginResponseDto;
import com.rpx.authenticationService.entity.User;

@Component
public class SetObject {

	public LoginResponseDto convertToLoginResponse(User obj, String token) {
		return new LoginResponseDto(obj.getUserName(), token, obj.getRoleId());
	}
}
