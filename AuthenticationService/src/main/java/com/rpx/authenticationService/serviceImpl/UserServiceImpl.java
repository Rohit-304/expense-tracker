package com.rpx.authenticationService.serviceImpl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rpx.authenticationService.dto.CustomResponse;
import com.rpx.authenticationService.dto.LoginRequestDto;
import com.rpx.authenticationService.dto.LoginResponseDto;
import com.rpx.authenticationService.dto.userDto;
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
	public CustomResponse userRegister(userDto dto) {
		try {
			Optional<User> userDetails = customizedUserDetailServiceImpl.getUserDetails();
			User user = setObject.convertUserDtoToEntity(dto);
			user.setPassword(dto.getPassword());
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			if (userDetails.isPresent()) {
				user.setCreatedOn(new Date());
				user.setCreatedBy(userDetails.get());
			}
			userRepository.save(user);
			return new CustomResponse(HttpStatus.OK.value(), null, "Registered Successful!");

		} catch (Exception e) {
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, HttpStatus.BAD_REQUEST.toString());
		}
	}

	@Override
	public CustomResponse updateUser(userDto dto) {
		try {

			Optional<User> loggedInUser = customizedUserDetailServiceImpl.getUserDetails();

			User user = userRepository.findById(dto.getId()).get();
			if (user == null || !user.getIsActive())
				return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "User Not Found");

			if (!user.getUserName().equalsIgnoreCase(dto.getUserName())
					&& userRepository.findByUserName(dto.getUserName()).isPresent())
				return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "UserName Already Taken");

			user.setName(dto.getName());
			user.setEmail(dto.getEmail());
			user.setMobileNumber(dto.getMobileNumber());
			user.setUserName(dto.getUserName());
			user.setUpdatedBy(loggedInUser.isPresent() ? loggedInUser.get() : null);
			user.setUpdatedOn(new Date());

			userRepository.save(user);

			return new CustomResponse(HttpStatus.OK.value(), null, "User Updated Successfully!");
		} catch (Exception e) {
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, HttpStatus.BAD_REQUEST.toString());
		}
	}

//	@Override
//	public CustomResponse updateUser(List<userDto> request) {
//		try {
//			Optional<User> userDetails = customizedUserDetailServiceImpl.getUserDetails();
//			List<String> userNameList = request.stream().filter(Objects::nonNull).map(userDto::getUserName).toList();
//			Map<String, User> userMap = userRepository.findByUserNameIn(userNameList).stream().filter(Objects::nonNull)
//					.collect(Collectors.toMap(User::getUserName, Function.identity()));
//			List<User> updateUserList = request.stream().filter(Objects::nonNull).map(e -> {
//				return setObject.convertUserDtoToEntity(e);
//			}).toList();
//
//			User loggedInUser = userDetails.get();
//			Date date = new Date();
//			if (updateUserList != null && !updateUserList.isEmpty()) {
//				for (User user : updateUserList) {
//					if (userMap.containsKey(user.getUserName())) {
//						User userToUpdate = userMap.get(user.getUserName());
//						if (user.getId() == null) {
//							userToUpdate.setName(user.getName());
//							userToUpdate.setUserName(user.getUserName());
//							userToUpdate.setEmail(user.getEmail());
//							userToUpdate.setMobileNumber(user.getMobileNumber());
//							user.setCreatedOn(date);
//							user.setCreatedBy(loggedInUser);
//						} else {
//							userToUpdate.setId(user.getId());
//							userToUpdate.setName(user.getName());
//							userToUpdate.setUserName(user.getUserName());
//							userToUpdate.setEmail(user.getEmail());
//							userToUpdate.setMobileNumber(user.getMobileNumber());
//							user.setUpdatedOn(date);
//							user.setUpdatedBy(loggedInUser);
//						}
//					}
//				}
//			}
//
//			userRepository.saveAll(updateUserList);
//			return new CustomResponse(HttpStatus.OK.value(), null, "Updated Successfully!");
//
//		} catch (Exception e) {
//			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, HttpStatus.BAD_REQUEST.toString());
//		}
//	}
}
