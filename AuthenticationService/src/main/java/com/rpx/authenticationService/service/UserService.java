package com.rpx.authenticationService.service;

import com.rpx.authenticationService.dto.CustomResponse;
import com.rpx.authenticationService.dto.LoginRequestDto;
import com.rpx.authenticationService.dto.RegisterRequestDto;

public interface UserService {

	CustomResponse getloginCredential(LoginRequestDto requestDto);

	CustomResponse userRegister(RegisterRequestDto requestDto);

}
