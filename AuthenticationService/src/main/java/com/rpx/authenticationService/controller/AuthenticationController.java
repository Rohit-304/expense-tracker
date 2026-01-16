package com.rpx.authenticationService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rpx.authenticationService.dto.CustomResponse;
import com.rpx.authenticationService.dto.LoginRequestDto;
import com.rpx.authenticationService.dto.userDto;
import com.rpx.authenticationService.service.UserService;
import com.rpx.authenticationService.util.Validation;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private UserService userService;

	@Autowired
	private Validation validation;

	@PostMapping(value = "/register")
	public ResponseEntity<?> saveOrUpdateUser(@RequestBody userDto request) {
		CustomResponse validationResponse = validation.validateUserRequest(request);
		if (validationResponse.getCode() == HttpStatus.OK.value()) {
			CustomResponse response = userService.userRegister(request);
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.ok(validationResponse);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<?> userLogin(@RequestBody LoginRequestDto request) {
		CustomResponse validationResponse = validation.validateLoginRequest(request);
		if (validationResponse.getCode() == HttpStatus.OK.value()) {
			CustomResponse response = userService.getloginCredential(request);
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.ok(validationResponse);
	}
}
