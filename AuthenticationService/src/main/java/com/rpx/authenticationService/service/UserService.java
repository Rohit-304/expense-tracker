package com.rpx.authenticationService.service;

import java.util.List;

import com.rpx.authenticationService.dto.CustomResponse;
import com.rpx.authenticationService.dto.LoginRequestDto;
import com.rpx.authenticationService.dto.userDto;

public interface UserService {

	CustomResponse getloginCredential(LoginRequestDto requestDto);

	CustomResponse saveOrUpdateUser(List<userDto> request);

	CustomResponse userRegister(userDto dto);

}
