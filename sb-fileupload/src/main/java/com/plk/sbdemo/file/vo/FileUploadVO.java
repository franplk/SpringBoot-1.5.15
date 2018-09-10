/**
 * 2018年8月29日 created by franp
 */
package com.plk.sbdemo.file.vo;

import java.io.Serializable;

/**
 * @author {AB052634/康培亮}
 *
 */
public class FileUploadVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String hostUrl;
	private String fileName;
	private String groupName;

	public String getHostUrl() {
		return hostUrl;
	}

	public FileUploadVO setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
		return this;
	}

	public String getFileName() {
		return fileName;
	}

	public FileUploadVO setFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}

	public String getGroupName() {
		return groupName;
	}

	public FileUploadVO setGroupName(String groupName) {
		this.groupName = groupName;
		return this;
	}
	
	public String getFileUrl() {
		return new StringBuffer(64).append(hostUrl)
				.append(groupName).append("/")
				.append(fileName).toString();
	}
}