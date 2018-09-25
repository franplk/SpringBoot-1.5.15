/**
 * 2018年8月29日 created by franp
 */
package com.plk.sbdemo.file.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 文件存储对象
 */
@Entity
public class FileModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String owner;// 文件属主
	private String fileName;// 源文件名
	private String fileMd5;// Md5
	private long fileSize;
	private Date uploadTime;
	private String fileUrl;

	public FileModel() {
		this.owner = "test";
		this.uploadTime = new Date();
	}

	/**
	 * @param fileMd5
	 * @param fileName
	 */
	public FileModel(String fileMd5, String fileName) {
		this();
		this.fileMd5 = fileMd5;
		this.fileName = fileName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileMd5() {
		return fileMd5;
	}

	public void setFileMd5(String fileMd5) {
		this.fileMd5 = fileMd5;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public void addSize(long chunkSize) {
		this.fileSize += chunkSize;
	}
}