package com.rpx.authentication.util;

import org.springframework.stereotype.Component;

import com.rpx.authentication.dto.LoginResponseDto;
import com.rpx.authentication.dto.userDto;
import com.rpx.authentication.entity.User;

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
