package com.plk.sbdemo.shiro.util;

import java.util.Random;

public class PasswordGenerator {

	public static String getStringRandom(int length) {
		StringBuffer val = new StringBuffer(length);
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			if (random.nextInt(2) % 2 == 0) {
				int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val.append((char)(random.nextInt(26) + temp));
			} else {
				val.append(random.nextInt(10));
			}
		}
		return val.toString();
	}

	public static void main(String[] args) {
		System.err.println(getStringRandom(8));
	}
}
