package com.rpx.authenticationService.util;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.rpx.authenticationService.dto.CustomResponse;
import com.rpx.authenticationService.dto.LoginRequestDto;
import com.rpx.authenticationService.dto.RegisterRequestDto;

@Component
public class Validation {

	public CustomResponse validateLoginRequest(LoginRequestDto request) {

		if (request.getUserName() == null || request.getUserName().isBlank())
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Please Provide Username");
		else if (request.getPassword() == null || request.getPassword().isBlank())
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Please Provide Password");

		return new CustomResponse(HttpStatus.OK.value(), null, "Ok");
	}

//	public CustomResponse validateRegisterRequest(RegisterRequestDto request) {
//		if (request.getName() == null || request.getName().isBlank())
//			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Please Provide Name");
//		else if (request.getUserName() == null || request.getUserName().isBlank())
//			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Please Provide UserName");
//		else if (request.getPassword() == null || request.getPassword().isBlank())
//			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Please Provide Password");
//		else if (request.getEmail() == null || request.getEmail().isBlank())
//			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Please Provide Email");
//		else if (request.getMobileNumber() == null || request.getMobileNumber().isBlank())
//			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Please Provide Mobile Number");
//
//		return new CustomResponse(HttpStatus.OK.value(), null, "Ok");
//
//	}

}
