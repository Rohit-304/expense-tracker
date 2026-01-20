package com.rpx.authenticationService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rpx.authenticationService.dto.CustomResponse;
import com.rpx.authenticationService.dto.userDto;
import com.rpx.authenticationService.service.UserService;
import com.rpx.authenticationService.util.Validation;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private Validation validation;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/v1/update")
	public ResponseEntity<?> updateUser(@RequestBody userDto dto) {
		CustomResponse validationResponse = validation.validateUserUpdateRequest(dto);
		if (validationResponse.getCode() == HttpStatus.OK.value()) {
			CustomResponse response = userService.updateUser(dto);
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.ok(validationResponse);
	}
}
