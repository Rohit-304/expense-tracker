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
import com.rpx.authenticationService.dto.RegisterRequestDto;
import com.rpx.authenticationService.service.UserService;
import com.rpx.authenticationService.util.Validation;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private UserService userService;

	@Autowired
	private Validation validation;

	@PostMapping
	public ResponseEntity<?> userRegister(@RequestBody RegisterRequestDto requestDto) {
		CustomResponse response = userService.userRegister(requestDto);
		return ResponseEntity.ok(response);
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
