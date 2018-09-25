package com.plk.sbdemo.file.fastdfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChunkConstants {

	private static final String CHECK_NUM_KEY = "chunk:num:key:";
	private static final String FASTDFS_FILE_KEY = "fastdfs:file:key:";
	private static final String FASTDFS_FILESIZE_KEY = "fastdfs:fileSize:key:";
	
	private static final Set<String> KeySet;
	
	static {
		KeySet = new HashSet<String>();
		KeySet.add(CHECK_NUM_KEY);
		KeySet.add(FASTDFS_FILE_KEY);
		KeySet.add(FASTDFS_FILESIZE_KEY);
	}

	public static String getFileSizeKey(String fileMd5) {
		return FASTDFS_FILESIZE_KEY + fileMd5;
	}
	
	public static String getCheckNumKey(String fileMd5) {
		return CHECK_NUM_KEY + fileMd5;
	}
	
	public static String getFilePathKey(String fileMd5) {
		return FASTDFS_FILE_KEY + fileMd5;
	}
	
	public static List<String> keys(String fileMd5) {
		List<String> keys = new ArrayList<>(KeySet.size());
		KeySet.forEach((k) -> keys.add(k + fileMd5));
		return keys;
	}
}
