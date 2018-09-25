package com.plk.sbdemo.file.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.plk.sbdemo.file.model.FileModel;
import com.plk.sbdemo.file.service.FileUploadService;
import com.plk.sbdemo.file.web.ApiResponse;

/**
 * 普通文件上传
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {

	@Autowired
	private FileUploadService fileUploadService;
	
	@GetMapping("/upload")
	public String fileUpload() {
		return "/upload";
	}

	@ResponseBody
	@PostMapping("/upload")
	public ApiResponse uploadFile(MultipartFile file) throws Exception {
		if (file.isEmpty()) {
			return ApiResponse.failResponse("没有选择文件");
		}
		
		String fullName = file.getOriginalFilename();
		FileModel fileModel = fileUploadService.uploadFile(fullName, file.getBytes());
		
		return ApiResponse.sucessResponse(fileModel);
	}
}
