/**
 * 2018年8月27日 created by franp
 */
package com.plk.sbdemo.file.model;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

import org.csource.common.NameValuePair;

/**
 * 文件模型
 */
public class FastFileModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String groupName;// 组名称
	private String extName;// 文件扩展名
	private byte[] fileContent;// 文件内容
	private Map<String, String> metaInfo;// 文件扩展信息

	public FastFileModel() {
	}

	/**
	 * 根据文件全路径名构造函数
	 * 
	 * @param fileFullName
	 */
	public FastFileModel(String fullName) {
		this.extName = fullName.substring(fullName.lastIndexOf(".") + 1);
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getExtName() {
		return extName;
	}

	public void setExtName(String extName) {
		this.extName = extName;
	}

	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	public Map<String, String> getMetaInfo() {
		return metaInfo;
	}

	public void setMetaInfo(Map<String, String> metaInfo) {
		this.metaInfo = metaInfo;
	}

	public NameValuePair[] metaList() {
		if (metaInfo == null || metaInfo.isEmpty()) {
			return null;
		}
		int i = 0;
		NameValuePair[] metaList = new NameValuePair[metaInfo.size()];
		for (Entry<String, String> meta : metaInfo.entrySet()) {
			NameValuePair pair = new NameValuePair(meta.getKey(), meta.getValue());
			metaList[i++] = pair;
		}
		return metaList;
	}
}
