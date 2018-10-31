package com.plk.sbdemo.admin.web.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.plk.sbdemo.admin.web.exception.excel.ExcelExportException;

@ControllerAdvice
public class PageExceptionDeploy {

	/**
	 * Excel文件下载异常
	 */
	@ExceptionHandler(ExcelExportException.class)
	public ModelAndView userException(Model model, ExcelExportException e) {
		return new ModelAndView("commons/failed", "errorMsg", e.getMessage());
	}

}
