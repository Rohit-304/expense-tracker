package com.rpx.authentication.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.rpx.authentication.dto.CustomResponse;
import com.rpx.authentication.dto.LoginRequestDto;
import com.rpx.authentication.dto.userDto;

@Component
public class Validation {

	public CustomResponse validateLoginRequest(LoginRequestDto request) {

		if (request.getUserName() == null || request.getUserName().isBlank())
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Please Provide Username");
		else if (request.getPassword() == null || request.getPassword().isBlank())
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Please Provide Password");

		return new CustomResponse(HttpStatus.OK.value(), null, "Ok");
	}

	public CustomResponse validateUserRequest(userDto request) {
		if (request.getName() == null || request.getName().isBlank())
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Please Provide Name");
		else if (!checkFullName(request.getName())) 
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), "Name should be valid.", null);
		else if (request.getUserName() == null || request.getUserName().isBlank())
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Please Provide UserName");
		else if (request.getPassword() == null || request.getPassword().isBlank())
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Please Provide Password");
		else if (request.getEmail() == null || request.getEmail().isBlank())
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Please Provide Email");
		else if (!checkMail(request.getEmail())) 
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null,"Email should be a valid.");
		else if (request.getMobileNumber() == null || request.getMobileNumber().isBlank())
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Please Provide Mobile Number");
		else if (!checkMobileNo(request.getMobileNumber()))
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null,"Mobile Number should be a valid.");

		return new CustomResponse(HttpStatus.OK.value(), null, "Ok");

	}
	// Name Validation
	public boolean checkFullName(String name) {
		return Pattern.matches("^[a-zA-Z](\\.|[a-zA-Z]+)(\\s[a-zA-Z]+|\\s[a-zA-Z]\\.){0,10}$", name);
	}

	// Email Validation
	public boolean checkMail(String mail) {
		return Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", mail);
	}

	// MobileNumber Validation
	public boolean checkMobileNo(String mobileNo) {
		return Pattern.matches("^(\\+\\d{1,3}[\\s-]?)?\\d{7,14}$", mobileNo);
	}

	public CustomResponse validateListOfUser(List<userDto> dto) {
		Map<Long, String> errorMessage = new HashMap<>();
		for (userDto user : dto) {
			CustomResponse validateUserRequest = validateUserRequest(user);
			if (validateUserRequest != null && validateUserRequest.getMessage() != null
					&& !validateUserRequest.getMessage().isEmpty()) {
				errorMessage.put(user.getId(), validateUserRequest.getMessage());
			}
		}
		return new CustomResponse(HttpStatus.OK.value(), errorMessage, "User Not Updated!");
	}

	public CustomResponse validateUserUpdateRequest(userDto request) {
		if (request.getId() == null || request.getId() < 0)
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Please Provide Id");
		else if (request.getName() == null || request.getName().isBlank())
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Please Provide Name");
		else if (!checkFullName(request.getName()))
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), "Name should be valid.", null);
		else if (request.getUserName() == null || request.getUserName().isBlank())
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Please Provide UserName");
		else if (request.getEmail() == null || request.getEmail().isBlank())
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Please Provide Email");
		else if (!checkMail(request.getEmail()))
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Email should be a valid.");
		else if (request.getMobileNumber() == null || request.getMobileNumber().isBlank())
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Please Provide Mobile Number");
		else if (!checkMobileNo(request.getMobileNumber()))
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Mobile Number should be a valid.");
		return new CustomResponse(HttpStatus.OK.value(), null, "ok");
	}
}
