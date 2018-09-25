package com.plk.sbdemo.file.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.plk.sbdemo.file.exception.chunk.ChunkFileException;
import com.plk.sbdemo.file.exception.fastdfs.FileUploadException;
import com.plk.sbdemo.file.web.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionDeploy {

	@ExceptionHandler(ChunkFileException.class)
	public ApiResponse chunkFileException (ChunkFileException ce) {
		return ApiResponse.failResponse(ce.getMessage());
	}
	
	@ExceptionHandler(FileUploadException.class)
	public ApiResponse fileUploadException (FileUploadException ce) {
		return ApiResponse.failResponse(ce.getMessage());
	}
	
	@ExceptionHandler(FileException.class)
	public ApiResponse fileException(FileException e) {
		return ApiResponse.failResponse(e.getMessage());
	}
}
