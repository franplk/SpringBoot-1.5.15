package com.plk.sbdemo.file.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.plk.sbdemo.file.exception.chunk.ChunkFileException;
import com.plk.sbdemo.file.model.FileModel;
import com.plk.sbdemo.file.service.ChunkUploadService;
import com.plk.sbdemo.file.web.ApiResponse;
import com.plk.sbdemo.file.web.query.ChunkUploadQuery;

@Controller
@RequestMapping("/chunk")
public class ChunkUploadController {

	@Autowired
	private ChunkUploadService chunkUploadService;

	/**
	 * 文件检查
	 */
	@ResponseBody
	@PostMapping("/fileCheck")
	public ApiResponse chunkFileCheck(String fileMd5, String fileName, long fileSize) {
		// 根据fileMd5查询
		chunkUploadService.fileCheck(fileMd5);

		return ApiResponse.sucessResponse();
	}

	/**
	 * chunk bolck 检查
	 */
	@ResponseBody
	@PostMapping("/chunkCheck")
	public ApiResponse chunkCheck(int chunkNum, String fileMd5) {
		// 根据fileMd5查询
		chunkUploadService.chunkCheck(chunkNum, fileMd5);

		return ApiResponse.sucessResponse();
	}

	@ResponseBody
	@PostMapping("/upload")
	public ApiResponse chunkUpload(ChunkUploadQuery query, MultipartFile file) throws Exception {
		if (file.isEmpty()) {
			throw new ChunkFileException("没有选择文件");
		}

		// 获取文件块信息
		byte[] content = file.getBytes();
//		long fileSize = file.getSize();
		String fullName = file.getOriginalFilename();
		int dotIndex = fullName.lastIndexOf(".");
		String extName = fullName.substring(dotIndex + 1);

		/*** 开始上传文件 ***/
		FileModel fileModel = chunkUploadService.chunkUpload(query, content, extName);

		return ApiResponse.sucessResponse(fileModel);
	}
}