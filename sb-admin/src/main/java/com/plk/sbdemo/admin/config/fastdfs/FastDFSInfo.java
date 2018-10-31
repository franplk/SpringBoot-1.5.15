package com.plk.sbdemo.admin.config.fastdfs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "fastdfs")
@PropertySource("classpath:config/fastdfs.properties")
public class FastDFSInfo {

	private String group;
	private String hosturl;
	private String confpath;

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getHosturl() {
		return hosturl;
	}

	public void setHosturl(String hosturl) {
		this.hosturl = hosturl;
	}
	
	public String getConfpath() {
		return confpath;
	}

	public void setConfpath(String confpath) {
		this.confpath = confpath;
	}

	public String fileLink(String fileName) {
		return new StringBuffer(128)
				.append(getHosturl()).append("/")
				.append(getGroup()).append("/")
				.append(fileName)
				.toString();
	}
}
