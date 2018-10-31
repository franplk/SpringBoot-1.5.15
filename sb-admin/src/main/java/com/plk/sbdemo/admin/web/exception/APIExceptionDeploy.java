package com.plk.sbdemo.admin.web.exception;

import org.apache.shiro.authc.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.plk.sbdemo.admin.web.ApiResponse;
import com.plk.sbdemo.admin.web.exception.file.ChunkFileException;
import com.plk.sbdemo.admin.web.exception.menu.MenuException;
import com.plk.sbdemo.admin.web.exception.quartz.QuartzException;
import com.plk.sbdemo.admin.web.exception.user.ApplyUserException;
import com.plk.sbdemo.admin.web.exception.user.PassUpdateException;

@RestControllerAdvice
public class APIExceptionDeploy {
	
	/**
	 * 用户申请异常
	 * @return
	 */
	@ExceptionHandler(ApplyUserException.class)
	public ApiResponse userException(ApplyUserException e) {
		return ApiResponse.fail(e.getMessage());
	}
	
	/**
	 * Shiro用户认证异常
	 * @return
	 */
	@ExceptionHandler(AuthenticationException.class)
	public ApiResponse loginException(AuthenticationException e) {
		return ApiResponse.fail(e.getMessage());
	}
	
	/**
	 * ，密码修改异常
	 * @return
	 */
	@ExceptionHandler(PassUpdateException.class)
	public ApiResponse passException(PassUpdateException e) {
		return ApiResponse.fail(e.getMessage());
	}
	
	/**
	 * 菜单管理异常
	 * @return
	 */
	@ExceptionHandler(MenuException.class)
	public ApiResponse menuException(MenuException e) {
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
	
	/**
	 * 文件分片上传异常
	 * @return
	 */
	@ExceptionHandler(ChunkFileException.class)
	public ApiResponse chunkFileException (ChunkFileException e) {
		return ApiResponse.fail(e.getMessage());
	}
}
