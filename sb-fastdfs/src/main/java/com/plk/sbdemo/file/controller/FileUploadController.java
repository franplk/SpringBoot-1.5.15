package com.plk.sbdemo.file.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.plk.sbdemo.file.model.FastFileModel;
import com.plk.sbdemo.file.service.FastDfsService;
import com.plk.sbdemo.file.vo.ApiResponse;

@Controller
@RequestMapping("/file")
public class FileUploadController {

	@Autowired
	private FastDfsService fileService;

	@GetMapping("")
	public String fileList() {
		return "UploadTpl";
	}

	@PostMapping("/upload")
	@ResponseBody
	public ApiResponse uploadFile(MultipartFile file) throws Exception {
		if (file.isEmpty()) {
			return ApiResponse.failResponse("文件为空");
		}
		String fileFullName = file.getOriginalFilename();
		FastFileModel model = new FastFileModel(fileFullName);
		model.setFileContent(file.getBytes());
		
		return ApiResponse.sucessResponse(fileService.uploadFile(model));
	}
}
