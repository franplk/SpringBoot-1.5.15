package com.plk.sbdemo.admin.config.shiro;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.plk.sbdemo.admin.domain.user.Permission;
import com.plk.sbdemo.admin.domain.user.Role;
import com.plk.sbdemo.admin.domain.user.User;
import com.plk.sbdemo.admin.service.user.UserService;

@Component
public class ShiroRealmBean extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;
	
	/* 
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
		
		UsernamePasswordToken userToken = (UsernamePasswordToken) token;
		
		// Verify User Account
		String username = userToken.getUsername();
		char[] creditials = userToken.getPassword();
		String password = new String(creditials);
		User user = userService.verifyUser(username, password);
		SecurityUtils.getSubject().getSession().setAttribute("account", user);
		
		// Create Authentication Info
		return new SimpleAuthenticationInfo(user.getId(), password, getName());
	}

	/* 
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		
		Long userid = (Long) principal.getPrimaryPrincipal();
		User user = userService.findUserById(userid);
		List<Role> roleList = user.getRoleList();
		roleList.forEach((role) -> {
			authorizationInfo.addRole(role.getRoleName());
			List<Permission> perms = role.getPermissionList();
			if (!perms.isEmpty()) {
				Set<String> permList = perms.stream().map(p -> p.getPermissionName()).collect(Collectors.toSet());
				authorizationInfo.addStringPermissions(permList);
			}
		});
		
		return authorizationInfo;
	}
}
