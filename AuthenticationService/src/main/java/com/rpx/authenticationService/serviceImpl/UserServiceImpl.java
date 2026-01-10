package com.rpx.authenticationService.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rpx.authenticationService.dto.CustomResponse;
import com.rpx.authenticationService.dto.LoginRequestDto;
import com.rpx.authenticationService.dto.LoginResponseDto;
import com.rpx.authenticationService.dto.RegisterRequestDto;
import com.rpx.authenticationService.entity.User;
import com.rpx.authenticationService.repository.UserRepository;
import com.rpx.authenticationService.service.JwtService;
import com.rpx.authenticationService.service.UserService;
import com.rpx.authenticationService.util.SetObject;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private SetObject setObject;

	@Override
	public CustomResponse getloginCredential(LoginRequestDto requestDto) {
		try {
			Optional<User> userOptional = userRepository.findByUserName(requestDto.getUserName());
			if (userOptional.isPresent()) {
				User user = userOptional.get();
				String token = jwtService.generateToken(user.getUserName());
				LoginResponseDto response = setObject.convertToLoginResponse(user, token);
				return new CustomResponse(HttpStatus.BAD_REQUEST.value(), response, "Login Successful!");

			}
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Invalid Credential");

		} catch (Exception e) {
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, HttpStatus.BAD_REQUEST.toString());
		}

	}

	@Override
	public void userRegister(RegisterRequestDto requestDto) {

	}

}
