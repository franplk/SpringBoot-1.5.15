package com.plk.sbdemo.admin.config.fastdfs;

import java.io.File;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;

@Configuration
public class FastDfsConfig {

	@Autowired
	private FastDFSInfo fastDFSInfo;
	
	@Bean
	public StorageClient storageClient() throws Exception {
		Assert.notNull(fastDFSInfo.getConfpath(), "FastDfs conf file can not be null");
		File cfgFile = ResourceUtils.getFile(fastDFSInfo.getConfpath());
		ClientGlobal.init(cfgFile.getAbsolutePath());
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackerServer = trackerClient.getConnection();
		return new StorageClient(trackerServer, null);
	}
	
}
