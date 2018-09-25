package com.plk.sbdemo.file.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 普通文件上传
 */
@Controller
@RequestMapping("/file")
public class FileDownloadController {

//
//	@ResponseBody
//	@PostMapping("/download")
//	public ApiResponse downLoadFile(String fileName, Integer offset, HttpServletResponse response) {
//		OutputStream output = null;
//		try {
//			output = response.getOutputStream();
//		} catch (IOException e) {
//			return ApiResponse.failResponse("获取客户端流失败");
//		}
//		
//		if (null == offset || offset < 0) {
//			offset = 0;
//		}
//		fileUploadService.downLoadFile(fileName, offset, output);
//		
//		return ApiResponse.sucessResponse();
//	}
}
