package com.plk.sbdemo.admin.config.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.plk.sbdemo.admin.web.interceptors.LoginInterceptor;

@Configuration
public class AppMvcConfigurer extends WebMvcConfigurerAdapter {

	/**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
        	.addPathPatterns("/**")
        	.excludePathPatterns("/login");
    }
}
