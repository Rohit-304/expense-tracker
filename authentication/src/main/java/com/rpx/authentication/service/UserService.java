package com.rpx.authentication.service;

import com.rpx.authentication.dto.CustomResponse;
import com.rpx.authentication.dto.LoginRequestDto;
import com.rpx.authentication.dto.userDto;

public interface UserService {

	CustomResponse getloginCredential(LoginRequestDto requestDto);

//	CustomResponse updateUser(List<userDto> request);

	CustomResponse userRegister(userDto dto);

	CustomResponse updateUser(userDto dto);

}
