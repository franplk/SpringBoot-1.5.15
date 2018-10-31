package com.plk.sbdemo.admin.config.shiro;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Authentication Configuration implements User Check
 */
@Configuration
public class ShiroConfiguration {

	/**
	 * 安全管理器
	 */
	@Bean
	public SecurityManager securityManager(ShiroRealmBean authorizingRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(authorizingRealm);
		return securityManager;
	}
	
	/**
	 * 授权管理设置
	 */
	@Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

	/**
	 * 认证过滤器Configure Filter Chain
	 * authc:所有url都必须认证通过才可以访问;
	 * anon:所有url都都可以匿名访问;
	 * user:remember me的可以访问
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		Map<String, String> filterMap = new HashMap<String, String>(8);
		
		// configure logout URL
		filterMap.put("/logout", "logout");
		
		// Configure URLS Not to be Authorized
		filterMap.put("/user/apply", "anon");
		filterMap.put("/verify", "anon");
		filterMap.put("/images/**", "anon");
		filterMap.put("/styles/**", "anon");
		filterMap.put("/scripts/**", "anon");
		filterMap.put("/easyui/**", "anon");
		filterMap.put("/druid/**", "anon");
		filterMap.put("/webuploader/**", "anon");
		
		// Configure URLS to be Authorized
		filterMap.put("/**", "authc");
		
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/index");
		shiroFilterFactoryBean.setUnauthorizedUrl("/error/403");
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
		
		return shiroFilterFactoryBean;
	}
}