package com.plk.sbdemo.file.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plk.sbdemo.file.exception.FileException;
import com.plk.sbdemo.file.fastdfs.service.FastDfsService;
import com.plk.sbdemo.file.model.FileModel;
import com.plk.sbdemo.file.repository.FileRepository;

@Service
public class FileService {
	
	@Autowired
	private FastDfsService dfsService;
	
	@Autowired
	private FileRepository fileRepository;
	
	@Transactional
	public void deleteFile(long fileId) {
		FileModel fileModel = fileRepository.getOne(fileId);
		if (fileModel == null) {
			throw new FileException("文件不存在");
		}
		// 先从数据库中删除
		fileRepository.delete(fileId);
		
		// 从文件服务器删除
		dfsService.deleteFile(fileModel.getFileName());
	}

	public FileModel findFileByFileMd5 (String fileMd5) {
		return fileRepository.findByFileMd5(fileMd5);
	}

	public List<FileModel> fileList() {
		return fileRepository.findAll();
	}
}
