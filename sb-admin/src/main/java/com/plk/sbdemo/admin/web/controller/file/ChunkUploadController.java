package com.plk.sbdemo.admin.web.controller.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.plk.sbdemo.admin.domain.file.ChunkFile;
import com.plk.sbdemo.admin.service.file.ChunkUploadService;
import com.plk.sbdemo.admin.web.ApiResponse;
import com.plk.sbdemo.admin.web.controller.param.ChunkUploadQuery;
import com.plk.sbdemo.admin.web.exception.file.ChunkFileException;

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
	public ApiResponse chunkFileCheck(String fileMd5, String fileName, Long fileSize) {
		// 根据fileMd5查询
		ChunkFile chunkFile = chunkUploadService.fileCheck(fileMd5, fileSize);

		return ApiResponse.success(chunkFile);
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
		ChunkFile fileModel = chunkUploadService.chunkUpload(query, content, extName);

		return ApiResponse.success(fileModel);
	}
}