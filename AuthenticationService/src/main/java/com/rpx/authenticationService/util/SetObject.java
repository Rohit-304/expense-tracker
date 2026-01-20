package com.rpx.authenticationService.util;

import org.springframework.stereotype.Component;

import com.rpx.authenticationService.dto.LoginResponseDto;
import com.rpx.authenticationService.dto.userDto;
import com.rpx.authenticationService.entity.User;

@Component
public class SetObject {

	public LoginResponseDto convertToLoginResponse(User obj, String token) {
		return new LoginResponseDto(obj.getUserName(), token, obj.getRoleId());
	}

	public User convertUserDtoToEntity(userDto obj) {
		return new User(obj.getId() != null ? obj.getId() : null, obj.getName(), obj.getUserName(), obj.getEmail(),
				obj.getMobileNumber(), obj.getRoleId(), true);
	}
}
