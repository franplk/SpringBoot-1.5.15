package com.plk.sbdemo.shiro.config;

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

	@Bean
	public SecurityManager securityManager(ShiroRealmBean authorizingRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(authorizingRealm);
		return securityManager;
	}

	// Filter Factory
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/index");
		shiroFilterFactoryBean.setUnauthorizedUrl("/error/403");
		
		/**
		 * Configure Filter Chain
		 * authc:所有url都必须认证通过才可以访问;
		 * anon:所有url都都可以匿名访问;
		 * user:remember me的可以访问
		 * */
		Map<String, String> filterMap = new HashMap<String, String>();
		
		// configure logout URL
		filterMap.put("/logout", "logout");
		
		// Configure URLS Not to be Authorized
		filterMap.put("/newer/**", "anon");
		filterMap.put("/verify", "anon");
		filterMap.put("/images/**", "anon");
		filterMap.put("/styles/**", "anon");
		filterMap.put("/scripts/**", "anon");
		
		// Configure URLS to be Authorized
		filterMap.put("/**", "authc");
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
		
		return shiroFilterFactoryBean;
	}
	
	@Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}