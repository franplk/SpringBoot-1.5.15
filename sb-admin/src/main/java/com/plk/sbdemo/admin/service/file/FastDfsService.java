/**
 * 2018年8月27日 created by franp
 */
package com.plk.sbdemo.admin.service.file;

import org.csource.fastdfs.StorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plk.sbdemo.admin.config.fastdfs.FastDFSInfo;
import com.plk.sbdemo.admin.web.exception.file.ChunkFileException;
import com.plk.sbdemo.admin.web.exception.file.FileException;

/**
 * 文件服务
 */
@Service
public class FastDfsService {

	@Autowired
	private FastDFSInfo fastDFSInfo;
	
	@Autowired
    private StorageClient storageClient;
	
	public String fileLink(String fileName) {
		return fastDFSInfo.fileLink(fileName);
	}
	
	/**
	 * 第一次分片上传
	 * @param fileContent
	 * @param extName
	 * @return
	 */
	public String firstChunk(byte[] fileContent, String extName) {
		String[] results = null;
		try {
			results = storageClient.upload_appender_file(fastDFSInfo.getGroup(), fileContent, extName, null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ChunkFileException("文件上传失败");
		}
		if (results == null) {
			throw new ChunkFileException("文件上传失败");
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
		int res = 0;
		try {
			res = storageClient.append_file(fastDFSInfo.getGroup(), sourceFile, content);
		} catch (Exception e) {
			throw new ChunkFileException("文件附加失败");
		}
		if (0 != res) {
			throw new ChunkFileException("文件附加失败");
		}
	}
	
	/**
	 * 删除文件
	 * 
	 * @param fileName
	 */
	public boolean deleteFile(String fileName) {
		try {
			int result = storageClient.delete_file(fastDFSInfo.getGroup(), fileName);
			return result == 0;
		} catch (Exception e) {
			throw new FileException("删除文件失败");
		}
	}
}
