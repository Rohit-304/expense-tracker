package com.rpx.authenticationService.util;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.rpx.authenticationService.dto.CustomResponse;
import com.rpx.authenticationService.dto.LoginRequestDto;

@Component
public class Validation {

	public CustomResponse validateLoginRequest(LoginRequestDto request) {

		if (request.getUserName() == null || request.getUserName().isBlank()) {
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Please Provide Username");
		} else if (request.getPassword() == null || request.getPassword().isBlank()) {
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Please Provide Password");
		}
		return new CustomResponse(HttpStatus.OK.value(), null, "Ok");
	}

}
