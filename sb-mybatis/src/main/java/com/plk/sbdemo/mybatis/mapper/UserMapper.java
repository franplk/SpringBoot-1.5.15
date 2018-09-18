package com.plk.sbdemo.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.plk.sbdemo.mybatis.domain.User;

/**
 * 使用方式之注解方式
 */
@Mapper
public interface UserMapper {

	@Insert("INSERT INTO USER(username,password,nickname,regtime,email) VALUES(#{username},#{password},#{nickname},#{regtime},#{email})")
	public void addEntity(User user);
	
	@Select("SELECT * FROM USER")
	public List<User> findAll();

}
