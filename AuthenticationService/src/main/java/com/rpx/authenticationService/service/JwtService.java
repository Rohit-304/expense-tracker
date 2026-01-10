package com.rpx.authenticationService.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

	public Boolean validateToken(String token, UserDetails userDetails);

	public String generateToken(String username);

	public String extractUsername(String token);

}
