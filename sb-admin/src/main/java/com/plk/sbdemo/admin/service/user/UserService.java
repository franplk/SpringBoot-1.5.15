package com.plk.sbdemo.admin.service.user;

import java.util.List;

import javax.mail.MessagingException;

import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plk.sbdemo.admin.constants.UserConstants;
import com.plk.sbdemo.admin.domain.email.MailTemplate;
import com.plk.sbdemo.admin.domain.user.User;
import com.plk.sbdemo.admin.repository.user.UserRepository;
import com.plk.sbdemo.admin.service.email.MailSendService;
import com.plk.sbdemo.admin.utils.PasswordGenerator;
import com.plk.sbdemo.admin.web.exception.user.ApplyUserException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MailSendService mailService;
	
	/**
	 * Add A New User
	 * @param username
	 * @param nickname
	 * @param email
	 */
	@Transactional
	@CacheEvict("UserCache")
	public User applyUser(String username, String nickname, String email) {
		// Verify The existence For the username
		User user = userRepository.findByUsername(username);
		if (null != user) {
			throw new ApplyUserException("用户名已存在");
		}
		
		// Create And Persist User With Random Password
		User newer = new User(username, email);
		newer.setNickname(nickname);
		newer.setPassword(PasswordGenerator.getStringRandom(8));
		newer = userRepository.save(newer);
		
		// Send A Mail To User
		try {
			MailTemplate<User> tpl = new MailTemplate<User>();
			tpl.setModelBean(newer);
			tpl.setModelName("userApply");
			tpl.setTplPath("email/userApplyNoticeTpl");
			tpl.setSubject("Account Apply Success");
			mailService.sendTemplateMail(email, tpl);
		} catch (MessagingException e) {
			throw new ApplyUserException("邮件发送异常");
		}
		
		return newer;
	}

	/**
	 * Shiro用户认证
	 * @param username
	 * @param password
	 */
	public User verifyUser(String username, String password) {
		// Verify UserName And Password
		User user = userRepository.findByUsernameAndPassword(username, password);
		if (null == user) {
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
	 * 删除用户
	 * @param userid
	 */
	@CacheEvict("UserCache")
	public void deleteUser(Long userid) {
		userRepository.delete(userid);
	}

	/**
	 * 冻结/解冻用户
	 * @param userid
	 * @param freeze true For freeze false for unfreeze
	 * @return
	 */
	@CacheEvict("UserCache")
	public User freezeUser(Long userid, boolean freeze) {
		User user = userRepository.findOne(userid);
		if (freeze) {
			user.setStatus(UserConstants.STSTUS_FREEZE);
		} else {
			user.setStatus(UserConstants.STSTUS_NORMAL);
		}
		return userRepository.save(user);
	}
	
	/**
	 * 根据用户ID查询用户
	 * @param userid
	 * @return
	 */
	@Cacheable("UserCache")
	public User findUserById(Long userid) {
		return userRepository.findOne(userid);
	}
	
	/**
	 * 查询所有用户
	 */
	@Cacheable("UserCache")
	public List<User> userList() {
		return userRepository.findAll();
	}
}
