package com.plk.sbdemo.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.plk.sbdemo.mybatis.domain.User;

/**
 * 使用方式之SQL的XML方式
 *
 */
@Mapper
public interface UserXMLMapper {

	public void addEntity(User user);
	
	public List<User> findAll();

}
