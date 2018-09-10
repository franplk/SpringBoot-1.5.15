/**
 * 2018年8月27日 created by franp
 */
package com.plk.sbdemo.file.service;

import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.plk.sbdemo.file.callback.BreakpointDownloadCallBack;
import com.plk.sbdemo.file.model.FastFileModel;
import com.plk.sbdemo.file.vo.FileUploadVO;

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
	public FileUploadVO uploadFile(FastFileModel model) {
		// 文件信息
		String extName = model.getExtName();
		String groupName = model.getGroupName();
		NameValuePair[] metaInfo = model.metaList();
		byte [] fileContent = model.getFileContent();
		
		// 上传文件
		String[] results = null;
		try {
			results = storageClient.upload_file(groupName, fileContent, extName, metaInfo);
		} catch (Exception e) {
			throw new RuntimeException("文件上传失败");
		}
		if (results == null) {
			throw new RuntimeException("文件上传失败");
		}
		
		// 返回文件名
		return new FileUploadVO()
				.setGroupName(results[0])
				.setFileName(results[1])
				.setHostUrl(hostUrl);
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
			throw new RuntimeException("删除文件失败");
		}
	}
	
	/**
	 * 获取文件元数据信息
	 * 
	 * @param groupname
	 * @param fileId
	 */
	public Map<String, String> getMetadata(String fileName) {
		return getMetadata(defaultGroup, fileName);
	}
	
	/**
	 * 获取文件元数据信息
	 * 
	 * @param groupname
	 * @param fileId
	 */
	public Map<String, String> getMetadata(String groupname, String fileName) {
		try {
			NameValuePair[] metaList = storageClient.get_metadata(groupname, fileName);
			if (metaList == null) {
				return Collections.emptyMap();
			}
			HashMap<String, String> map = new HashMap<String, String>(metaList.length);
			for (NameValuePair metaItem : metaList) {
				map.put(metaItem.getName(), metaItem.getValue());
			}
			return map;
		} catch (Exception e) {
			throw new RuntimeException("获取文件元数据信息失败");
		}
	}
	
	/**
	 * 获取文件内容
	 * 
	 * @param fileName
	 */
	public byte[] getFileContent(String fileName) {
		return getFileContent(defaultGroup, fileName);
	}
	
	/**
	 * 获取文件内容
	 * 
	 * @param fileName
	 */
	public byte[] getFileContent(String group, String fileName) {
		try {
			return storageClient.download_file(defaultGroup, fileName);
		} catch (Exception e) {
			throw new RuntimeException("获取文件内容失败");
		}
	}
	
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
			throw new RuntimeException("获取文件内容失败");
		}
	}

	public String getDefaultGroup() {
		return defaultGroup;
	}

	public void setDefaultGroup(String defaultGroup) {
		this.defaultGroup = defaultGroup;
	}
}
