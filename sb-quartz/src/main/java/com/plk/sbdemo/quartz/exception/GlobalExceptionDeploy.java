package com.plk.sbdemo.quartz.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.plk.sbdemo.quartz.exception.menu.MenuException;
import com.plk.sbdemo.quartz.exception.quartz.QuartzException;
import com.plk.sbdemo.quartz.exception.user.UserException;
import com.plk.sbdemo.quartz.web.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionDeploy {
	
	/**
	 * 用户管理异常
	 * @return
	 */
	@ExceptionHandler(MenuException.class)
	public ApiResponse menuException(MenuException e) {
		return ApiResponse.fail(e.getMessage());
	}
	
	/**
	 * 菜单管理异常
	 * @return
	 */
	@ExceptionHandler(UserException.class)
	public ApiResponse userException(UserException e) {
		return ApiResponse.fail(e.getMessage());
	}
	
	/**
	 * 定时任务管理异常处理
	 * @return
	 */
	@ExceptionHandler(QuartzException.class)
	public ApiResponse quartzException(QuartzException e) {
		return ApiResponse.fail(e.getMessage());
	}
}
