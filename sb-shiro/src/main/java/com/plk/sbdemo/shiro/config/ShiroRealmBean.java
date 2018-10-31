package com.plk.sbdemo.shiro.config;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
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

import com.plk.sbdemo.shiro.domain.Permission;
import com.plk.sbdemo.shiro.domain.User;
import com.plk.sbdemo.shiro.service.UserService;

@Component
public class ShiroRealmBean extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;
	
	/* 
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		UsernamePasswordToken userToken = (UsernamePasswordToken) token;
		
		String username = userToken.getUsername();
		if (null == username || "".equals(username)) {
			throw new AuthenticationException("用户名为空");
		}
		char[] creditials = userToken.getPassword();
		if (null == creditials || creditials.length == 0) {
			throw new AuthenticationException("账户密码为空");
		}
		
		// Verify User Account
		String password = new String(creditials);
		User user = userService.authentication(username, password);
		
		// Create Authentication Info
		AuthenticationInfo authInfo = new SimpleAuthenticationInfo(user.getId(), password, getName());
		
		SecurityUtils.getSubject().getSession().setAttribute("account", user);
		
        return authInfo;
	}

	/* 
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		
		Long userid = (Long) principal.getPrimaryPrincipal();
		Map<String, List<Permission>> roleAndPermMap = userService.findRoleAndPermission(userid);
		roleAndPermMap.forEach((r,plist) -> {
			authorizationInfo.addRole(r);
			if (!plist.isEmpty()) {
				Set<String> permList = plist.stream()
						.map(p -> p.getPermissionName())
						.collect(Collectors.toSet());
				authorizationInfo.addStringPermissions(permList);
			}
		});
		
		return authorizationInfo;
	}
}
