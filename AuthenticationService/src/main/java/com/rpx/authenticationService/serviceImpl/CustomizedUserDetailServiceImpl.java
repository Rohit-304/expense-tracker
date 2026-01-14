package com.rpx.authenticationService.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rpx.authenticationService.entity.User;
import com.rpx.authenticationService.repository.UserRepository;

@Service
public class CustomizedUserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOptional = userRepository.findByUserName(username);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			Collection<String> authorities = new ArrayList<>();
			authorities.add(user.getRole().getName());
			UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUserName(),
					user.getPassword(), getAuthorities(authorities));
			return userDetails;
		} else {
			throw new UsernameNotFoundException("User not found");
		}
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Collection<String> authorities) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		for (String role : authorities) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role));
		}
		return grantedAuthorities;
	}

	public Optional<User> getUserDetails() {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Optional<User> user = userRepository.findByUserName(auth.getName());
			return user;
		} catch (Exception e) {
			return Optional.empty();
		}
	}
}
