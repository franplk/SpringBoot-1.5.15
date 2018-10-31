package com.plk.sbdemo.shiro.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.plk.sbdemo.shiro.config.UserConstants;
import com.plk.sbdemo.shiro.domain.Permission;
import com.plk.sbdemo.shiro.domain.User;
import com.plk.sbdemo.shiro.domain.UserRole;
import com.plk.sbdemo.shiro.exception.ApplyUserException;
import com.plk.sbdemo.shiro.exception.UserException;
import com.plk.sbdemo.shiro.repository.UserRepository;
import com.plk.sbdemo.shiro.util.PasswordGenerator;

@Service
public class UserService {

	@Autowired
	private UserRepository userDao;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * 查询用户对应的role以及permission
	 * @param userid
	 * @return
	 */
	public Map<String, List<Permission>> findRoleAndPermission(Long userid) {
		List<UserRole> userRoleList = userRoleService.findUserRole(userid);
		if (null == userRoleList || userRoleList.isEmpty()) {
			return Collections.emptyMap();
		}
		return userRoleList.stream().map(ur -> ur.getRoleid())
				.collect(Collectors.toMap(rid -> roleService.getRoleNameById(rid), rid -> roleService.getPermissonsByRoleId(rid)));
	}
	
	/**
	 * Add A New User
	 * @param username
	 * @param nickname
	 * @param email
	 */
	@Transactional
	public User applyUser(String username, String nickname, String email) {
		if (StringUtils.isEmpty(username)) {
			throw new ApplyUserException("用户名不能为空");
		}
		if (StringUtils.isEmpty(email)) {
			throw new ApplyUserException("邮箱地址接收密码不能为空");
		}
		
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setNickname(nickname);
		
		// Create Random Password
		String password = PasswordGenerator.getStringRandom(8);
		user.setPassword(password);
		
		user = userDao.save(user);
		
		// Send A Mail To User
		
		return user;
	}
	
	/**
	 * Verify User Account
	 * @param username
	 * @param password
	 * @return
	 */
	public User authentication(String username, String password) {
		// Verify UserName
		User user = userDao.findByUsername(username);
		if (null == user) {
			throw new AuthenticationException("用户不存在");
		}
		
		// Verify Password
		if (!user.getPassword().equals(password)) {
			throw new AuthenticationException("用户名或密码不对");
		}
		
		// Verify User Status
		int status = user.getStatus();
		if (status == UserConstants.STSTUS_FREEZE) {
			throw new AuthenticationException("您的账号已被冻结");
		}
		
		return user;
	}
	
	/**
	 * Delete A User By ID
	 * @param userid
	 */
	public void delUser(Long userid) {
		userDao.delete(userid);
	}
	
	/**
	 * Freeze A User By ID
	 * @param userid
	 */
	public void freezeUser(Long userid) {
		User user = userDao.findOne(userid);
		if (null == user) {
			throw new UserException("用户不存在");
		}
		user.setStatus(UserConstants.STSTUS_FREEZE);
		userDao.save(user);
	}

	public List<User> findUser() {
		return userDao.findAll();
	}
}
