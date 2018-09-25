package com.plk.sbdemo.file.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plk.sbdemo.file.fastdfs.service.FastDfsService;
import com.plk.sbdemo.file.model.FileModel;
import com.plk.sbdemo.file.repository.FileRepository;

@Service
public class FileUploadService {

	@Autowired
	private FastDfsService dfsService;
	
	@Autowired
	private FileRepository fileRepository;

	public FileModel uploadFile(String fullName, byte[] fileContent) {
		int dotIndex = fullName.lastIndexOf(".");
		String extName = fullName.substring(dotIndex + 1);
		FileModel fileModel = dfsService.uploadFile(fileContent, extName);
		fileModel.setUploadTime(new Timestamp(System.currentTimeMillis()));
		return fileRepository.save(fileModel);
	}
}
