package com.plk.sbdemo.admin.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plk.sbdemo.admin.domain.user.User;
import com.plk.sbdemo.admin.repository.user.UserRepository;
import com.plk.sbdemo.admin.web.exception.user.PassUpdateException;

@Service
public class AccountService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * 修改用户密码
	 * @param userid
	 * @param newPass
	 * @param repeatPass
	 */
	public void passUpdate(Long userid, String newPass, String repPass) {
		if (!newPass.equals(repPass)) {
			throw new PassUpdateException("两次输入的密码不一致");
		}
		
		User user = userRepository.findOne(userid);
		if (newPass.equals(user.getPassword())) {
			throw new PassUpdateException("新密码与旧密码不能相同");
		}
		
		user.setPassword(newPass);
		userRepository.save(user);
	}
}
