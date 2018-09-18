package com.plk.sbdemo.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plk.sbdemo.jpa.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
