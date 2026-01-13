package com.rpx.authenticationService.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.rpx.authenticationService.dto.LoginResponseDto;
import com.rpx.authenticationService.dto.RegisterRequestDto;
import com.rpx.authenticationService.entity.User;

@Component
public class SetObject {

	public LoginResponseDto convertToLoginResponse(User obj, String token) {
		return new LoginResponseDto(obj.getUserName(), token, obj.getRoleId());
	}

	public User convertRegisterRequestToUserEntity(RegisterRequestDto obj) {
		return new User(null, obj.getName(), obj.getUserName(), obj.getPassword(), obj.getEmail(),
				obj.getMobileNumber(), obj.getRoleId(), true, new Date(), null, null, null);
	}
}
