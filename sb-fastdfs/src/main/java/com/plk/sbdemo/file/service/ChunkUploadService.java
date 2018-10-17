package com.plk.sbdemo.file.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.plk.sbdemo.file.exception.chunk.ChunkFileException;
import com.plk.sbdemo.file.fastdfs.ChunkConstants;
import com.plk.sbdemo.file.fastdfs.service.FastDfsService;
import com.plk.sbdemo.file.model.FileModel;
import com.plk.sbdemo.file.redis.service.RedisService;
import com.plk.sbdemo.file.repository.FileRepository;
import com.plk.sbdemo.file.web.query.ChunkUploadQuery;

/**
 * 文件分片上传 1. 文件校验 2. 分块校验 3. 分片合并
 */
@Service
public class ChunkUploadService {
	
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private FastDfsService dfsService;
	
	@Autowired
	private FileRepository fileRespository;
	
	/**
	 * 文件检查
	 * @param fileMd5
	 * @return
	 */
	public void fileCheck(String fileMd5) {
		// 参数校验
		if (StringUtils.isEmpty(fileMd5)) {
			throw new ChunkFileException("fileMd5不能为空");
		}
		// 检查是否有该文件(MD5)
		FileModel fileModel = fileRespository.findByFileMd5(fileMd5);
		if (null != fileModel) {
			throw new ChunkFileException("文件已存在");
		}
		
		// 初始化设置chunk block
		String chunkNumKey = ChunkConstants.getCheckNumKey(fileMd5);
		redisService.setCache(chunkNumKey, "0");
	}
	
	/**
	 * 文件分片检查
	 * @param chunkNum
	 * @param fileMd5
	 * @return
	 */
	public void chunkCheck(int chunkNum, String fileMd5) {
		// 参数校验
		if (StringUtils.isEmpty(fileMd5)) {
			throw new ChunkFileException("fileMd5不能为空");
		}
		// 查询文件块
		String chunkNumKey = ChunkConstants.getCheckNumKey(fileMd5);
		String chunkLast = redisService.getCache(chunkNumKey);
		if (StringUtils.isEmpty(chunkLast)) {
			throw new ChunkFileException("无法获取文件当前块");
		}
		// 分片对比
		int chunkLastNum = Integer.parseInt(chunkLast);
		if (chunkNum < chunkLastNum) {
			throw new ChunkFileException("当前文件块已上传");
		} else if (chunkNum > chunkLastNum) {
			throw new ChunkFileException("当前文件块需要等待上传,稍后请重试");
		}
	}
	
	/**
	 * 分片上传
	 * @param query
	 * @param fileMd5
	 * @param content
	 * @param extName
	 */
	public FileModel chunkUpload(ChunkUploadQuery query, byte[] content, String extName) {
		String fileMd5 = query.getFileMd5();
		String fastDfsFile = null;
		String fastDFSFileKey = ChunkConstants.getFilePathKey(fileMd5);
		if (query.firstChunk()) {// 如果是第一块
			fastDfsFile = dfsService.firstChunk(content, extName);
			redisService.setCache(fastDFSFileKey, fastDfsFile);
		} else {// 非第一块
			fastDfsFile = redisService.getCache(fastDFSFileKey);
			if (fastDfsFile == null) {
				throw new ChunkFileException("无法获取上传远程服务器文件出错");
			}
			dfsService.chunkAppender(fastDfsFile, content);
		}
		
		// 文件大小记录
		String fileSizeKey = ChunkConstants.getFileSizeKey(fileMd5);
		redisService.increment(fileSizeKey, content.length);
		
		// 当前分片上传成功
		if (query.endChunk()) {// 最后一块
			String fileUrl = dfsService.fileLink(fastDfsFile);
			String fileSize = redisService.getCache(fileSizeKey);
			// 写入数据库
			FileModel fileModel = new FileModel(fileMd5, fastDfsFile);
			fileModel.setFileSize(Long.parseLong(fileSize));
			fileModel.setFileUrl(fileUrl);
			fileRespository.save(fileModel);
			// 清空缓存
			redisService.deleteCache(ChunkConstants.keys(fileMd5));
			return fileModel;
		}
		String chunkNumKey = ChunkConstants.getCheckNumKey(fileMd5);
		redisService.increment(chunkNumKey, 1);
		
		return null;
	}
}