/**
 * 2018年8月27日 created by franp
 */
package com.plk.sbdemo.file.service;

import java.io.OutputStream;

import org.csource.fastdfs.StorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.plk.sbdemo.file.exception.fastdfs.FileUploadException;
import com.plk.sbdemo.file.fastdfs.BreakpointDownloadCallBack;

/**
 * 文件服务
 */
@Service
public class BreakPointDownloadService {

	@Value("${fastdfs.conf.default-group}")
	private String defaultGroup;
	
	@Autowired
    private StorageClient storageClient;
	
	/**
	 * 断点下载
	 * 
	 * @param fileName
	 */
	public int breakpointDownload(String fileName, int offset, OutputStream output) {
		return this.breakpointDownload(defaultGroup, fileName, offset, output);
	}
	
	/**
	 * 断点下载
	 * 
	 * @param fileName
	 */
	public int breakpointDownload(String group, String fileName, long offset, OutputStream output) {
		try {
			BreakpointDownloadCallBack callBack = new BreakpointDownloadCallBack(offset, output);
			return storageClient.download_file(group, fileName, callBack);
		} catch (Exception e) {
			throw new FileUploadException("文件下载失败");
		}
	}

	public String getDefaultGroup() {
		return defaultGroup;
	}

	public void setDefaultGroup(String defaultGroup) {
		this.defaultGroup = defaultGroup;
	}
}
