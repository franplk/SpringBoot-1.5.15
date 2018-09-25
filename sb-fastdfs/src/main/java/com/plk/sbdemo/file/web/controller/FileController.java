package com.plk.sbdemo.file.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plk.sbdemo.file.model.FileModel;
import com.plk.sbdemo.file.service.FileService;
import com.plk.sbdemo.file.web.ApiResponse;

@Controller
@RequestMapping("/file")
public class FileController {

	@Autowired
	private FileService fileService;

	@GetMapping("")
	public String fileList(Model model) {
		// Read FileList From DB
		List<FileModel> fileList = fileService.fileList();

		model.addAttribute("fileList", fileList);

		return "/filelist";
	}

	@ResponseBody
	@PostMapping("/delete")
	public ApiResponse fileDelete(Long fileId) {
		
		fileService.deleteFile(fileId);
		
		return ApiResponse.sucessResponse();
	}
}
