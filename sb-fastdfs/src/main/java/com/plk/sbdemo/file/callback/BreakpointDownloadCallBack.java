/**
 * 2018年8月28日 created by franp
 */
package com.plk.sbdemo.file.callback;

import java.io.OutputStream;

import org.csource.fastdfs.DownloadCallback;

/**
 * 下载断点后继续下载的回调函数
 * 
 * 下载过程中，出现故障，返回当前已下载的文件起始位置
 * 
 */
public class BreakpointDownloadCallBack implements DownloadCallback {

	private long offset = 0;
	private OutputStream output;
	
	public BreakpointDownloadCallBack(long offset, OutputStream output) {
		this.offset = offset;
		this.output = output;
	}
	
	@Override
	public int recv(long file_size, byte[] data, int bytes) {
		try {
			output.write(data);
			offset += bytes;
			return 0;
		} catch (Exception e) {
			return 1;
		} finally {
			// 保存当前已传输的字节数
			savePro(offset);
		}
	}
	
	private void savePro(long offset) {
		
	}
}
