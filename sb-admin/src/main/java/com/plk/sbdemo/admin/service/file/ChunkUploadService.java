package com.plk.sbdemo.admin.service.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plk.sbdemo.admin.domain.file.ChunkFile;
import com.plk.sbdemo.admin.repository.file.FileRepository;
import com.plk.sbdemo.admin.web.controller.param.ChunkUploadQuery;
import com.plk.sbdemo.admin.web.exception.file.ChunkFileException;

/**
 * 文件分片上传 1. 文件校验 2. 分块校验 3. 分片合并
 */
@Service
public class ChunkUploadService {
	
	@Autowired
	private FastDfsService dfsService;
	
	@Autowired
	private FileRepository fileRespository;
	
	/**
	 * 文件检查
	 * @param fileMd5
	 * @return
	 */
	public ChunkFile fileCheck(String fileMd5, Long fileSize) {
		// 检查是否有该文件(MD5)
		ChunkFile chunkFile = fileRespository.findByFileMd5(fileMd5);
		if (null == chunkFile) {// 初始化设置chunk block
			chunkFile = new ChunkFile(fileMd5, fileSize);
			fileRespository.save(chunkFile);
		} else if (chunkFile.getStatus() == 0) {
			throw new ChunkFileException("文件已存在");
		}
		
		return chunkFile;
	}
	
	/**
	 * 分片上传
	 * @param query
	 * @param fileMd5
	 * @param content
	 * @param extName
	 */
	public ChunkFile chunkUpload(ChunkUploadQuery query, byte[] content, String extName) {
		ChunkFile chunkFile = fileRespository.findByFileMd5(query.getFileMd5());
		if (query.firstChunk()) {// 如果是第一块
			String fastDfsFile = dfsService.firstChunk(content, extName);
			String fileUrl = dfsService.fileLink(fastDfsFile);
			chunkFile.setFileName(fastDfsFile); 
			chunkFile.setFileUrl(fileUrl);
		} else {// 非第一块
			dfsService.chunkAppender(chunkFile.getFileName(), content);
		}
		
		// 当前分片上传成功
		if (query.endChunk()) {// 最后一块.更新数据库状态
			chunkFile.setStatus(0);
		} else {
			chunkFile.addChunkNum();
		}
		
		return fileRespository.save(chunkFile);
	}
}