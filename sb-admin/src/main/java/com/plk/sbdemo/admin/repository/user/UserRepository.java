package com.plk.sbdemo.admin.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plk.sbdemo.admin.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
	User findByUsernameAndPassword(String username, String password);

}
