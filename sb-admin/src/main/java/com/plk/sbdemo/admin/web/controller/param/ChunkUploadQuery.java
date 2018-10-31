package com.plk.sbdemo.admin.web.controller.param;

public class ChunkUploadQuery {

	private int chunk = 0;// 文件分块
	private int chunks = 1;// 文件分块数
	private String fileMd5;
	private int chunkSize;// 分块大小

	public String getFileMd5() {
		return fileMd5;
	}

	public void setFileMd5(String fileMd5) {
		this.fileMd5 = fileMd5;
	}

	public int getChunk() {
		return chunk;
	}

	public void setChunk(int chunk) {
		this.chunk = chunk;
	}

	public int getChunks() {
		return chunks;
	}

	public void setChunks(int chunks) {
		this.chunks = chunks;
	}

	public int getChunkSize() {
		return chunkSize;
	}

	public void setChunkSize(int chunkSize) {
		this.chunkSize = chunkSize;
	}
	
	public boolean firstChunk() {
		return chunk == 0;
	}

	public boolean endChunk() {
		return chunk + 1 == chunks;
	}

	public long offset() {
		return chunk * chunkSize;
	}
}
