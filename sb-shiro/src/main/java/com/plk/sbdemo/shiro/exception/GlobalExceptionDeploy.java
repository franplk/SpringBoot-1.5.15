package com.plk.sbdemo.shiro.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.plk.sbdemo.shiro.model.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionDeploy {

	/**
	 * 未授权异常捕获
	 * @return
	 */
	@ExceptionHandler(UnauthorizedException.class)
	public ApiResponse unAutority() {
		return ApiResponse.failResponse("您无权操作");
	}
	
	/**
	 * 未授权异常捕获
	 * @return
	 */
	@ExceptionHandler(UserException.class)
	public ApiResponse userException(UserException e) {
		return ApiResponse.failResponse(e.getMessage());
	}
}
