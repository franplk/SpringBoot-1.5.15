/**
 * 2018年8月27日 created by franp
 */
package com.plk.sbdemo.file.fastdfs.service;

import org.csource.fastdfs.StorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.plk.sbdemo.file.exception.FileException;
import com.plk.sbdemo.file.exception.fastdfs.FileUploadException;
import com.plk.sbdemo.file.model.FileModel;

/**
 * 文件服务
 */
@Service
public class FastDfsService {

	@Value("${fastdfs.conf.default-group}")
	private String defaultGroup;
	
	@Value("${fastdfs.conf.host-url}")
	private String hostUrl;
	
	@Autowired
    private StorageClient storageClient;
	
	
	
	/**
	 * 上传文件
	 * 
	 * @param model
	 */
	public FileModel uploadFile(byte[] fileContent, String extName) {
		return uploadFile(defaultGroup, fileContent, extName);
	}
	
	/**
	 * 上传文件
	 * 
	 * @param model
	 */
	public FileModel uploadFile(String groupName, byte[] fileContent, String extName) {
		// 上传文件
		String[] results = null;
		try {
			results = storageClient.upload_file(groupName, fileContent, extName, null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileUploadException("文件上传失败");
		}
		if (results == null) {
			throw new FileUploadException("文件上传失败");
		}
		
		// 返回文件
		FileModel model = new FileModel();
		String fileUrl = fileLink(results[0], results[1]);
		model.setFileUrl(fileUrl);
		
		return model;
	}
	
	public String fileLink(String fileName) {
		return fileLink(defaultGroup, fileName);
	}
	
	public String fileLink(String group, String fileName) {
		return new StringBuffer(128)
				.append(hostUrl).append("/")
				.append(group).append("/")
				.append(fileName)
				.toString();
	}
	
	/**
	 * 第一次分片上传
	 * @param fileContent
	 * @param extName
	 * @return
	 */
	public String firstChunk(byte[] fileContent, String extName) {
		return firstChunk(defaultGroup, fileContent, extName);
	}

	/**
	 * 第一次分片上传
	 * @param groupName
	 * @param fileContent
	 * @param extName
	 * @return
	 */
	public String firstChunk(String groupName, byte[] fileContent, String extName) {
		String[] results = null;
		try {
			results = storageClient.upload_appender_file(groupName, fileContent, extName, null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileUploadException("文件上传失败");
		}
		if (results == null) {
			throw new FileUploadException("文件上传失败");
		}
		return results[1];
	}

	/**
	 * 附加分片文件
	 * @param groupName
	 * @param sourceFile
	 * @param content
	 * @throws Exception
	 */
	public void chunkAppender(String sourceFile, byte[] content) {
		chunkAppender(defaultGroup, sourceFile, content);
	}
	
	/**
	 * 附加分片文件
	 * @param groupName
	 * @param sourceFile
	 * @param content
	 * @throws Exception
	 */
	public void chunkAppender(String groupName, String sourceFile, byte[] content) {
		int res = 0;
		try {
			res = storageClient.append_file(groupName, sourceFile, content);
		} catch (Exception e) {
			throw new FileUploadException("文件附加失败");
		}
		if (0 != res) {
			throw new FileUploadException("文件附加失败");
		}
	}
	
	/**
	 * 删除文件
	 * 
	 * @param fileName
	 */
	public boolean deleteFile(String fileName) {
		return deleteFile(defaultGroup, fileName);
	}
	
	/**
	 * 删除文件
	 * 
	 * @param fileName
	 */
	public boolean deleteFile(String group, String fileName) {
		try {
			int result = storageClient.delete_file(group, fileName);
			return result == 0;
		} catch (Exception e) {
			throw new FileException("删除文件失败");
		}
	}

	public String getDefaultGroup() {
		return defaultGroup;
	}

	public void setDefaultGroup(String defaultGroup) {
		this.defaultGroup = defaultGroup;
	}
}
