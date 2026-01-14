package com.rpx.authenticationService.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	private CustomizedUserDetailServiceImpl customizedUserDetailServiceImpl;

	@Autowired
	private PasswordEncoder passwordEncoder;

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
				if (passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {

					String token = jwtService.generateToken(user.getUserName());
					LoginResponseDto response = setObject.convertToLoginResponse(user, token);
					return new CustomResponse(HttpStatus.OK.value(), response, "Login Successful!");
				}
				return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Incorrect Password");
			}
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Invalid Credential");

		} catch (Exception e) {
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, HttpStatus.BAD_REQUEST.toString());
		}

	}

	@Override
	public CustomResponse userRegister(RegisterRequestDto requestDto) {
		try {
			Optional<User> userDetails = customizedUserDetailServiceImpl.getUserDetails();
			User user = setObject.convertRegisterRequestToUserEntity(requestDto);
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			if (userDetails.isPresent())
				user.setCreatedBy(userDetails.get());
			userRepository.save(user);
			return new CustomResponse(HttpStatus.OK.value(), null, "Registered Successful!");

		} catch (Exception e) {
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, HttpStatus.BAD_REQUEST.toString());
		}
	}

}
