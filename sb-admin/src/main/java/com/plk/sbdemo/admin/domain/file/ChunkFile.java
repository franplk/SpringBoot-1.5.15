/**
 * 2018年8月29日 created by franp
 */
package com.plk.sbdemo.admin.domain.file;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 文件存储对象
 */
@Entity
public class ChunkFile implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String fileName;// 源文件名
	private String fileMd5;// Md5
	private long fileSize;
	private String fileUrl;
	private Integer chunkNum;// 已经上传到第几块
	private Integer status;// 0-完成 1-未完成

	public ChunkFile() {
		this.chunkNum = 0;
		this.status = 1;
	}
	
	public ChunkFile(String fileMd5) {
		this();
		this.fileMd5 = fileMd5;
	}
	
	public ChunkFile(String fileMd5, long fileSize) {
		this();
		this.fileMd5 = fileMd5;
		this.fileSize = fileSize;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileMd5() {
		return fileMd5;
	}

	public void setFileMd5(String fileMd5) {
		this.fileMd5 = fileMd5;
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

	public void addSize(long chunkSize) {
		this.fileSize += chunkSize;
	}

	public Integer getChunkNum() {
		return chunkNum;
	}

	public void setChunkNum(Integer chunkNum) {
		this.chunkNum = chunkNum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void addChunkNum() {
		this.chunkNum = chunkNum + 1;
	}
}