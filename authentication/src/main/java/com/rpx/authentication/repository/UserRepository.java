package com.rpx.authentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rpx.authentication.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	Optional<User> findByEmail(String username);

	Optional<User> findByUserName(String userName);

	List<User> findByUserNameIn(List<String> userNameList);
	
}
