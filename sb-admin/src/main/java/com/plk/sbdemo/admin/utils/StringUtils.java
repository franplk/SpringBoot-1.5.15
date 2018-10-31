package com.plk.sbdemo.admin.utils;

public class StringUtils {

	private StringUtils() {}
	
	/**
	 * 首字母大写
	 * @param str
	 * @return
	 */
	public static String toFirstUpperCase(String str) {
		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		return new String(ch);
	}
}
