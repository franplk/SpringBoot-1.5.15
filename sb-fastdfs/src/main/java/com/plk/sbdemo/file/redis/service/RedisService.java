package com.plk.sbdemo.file.redis.service;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * Use Mode-1: Using {@link RedisTemplate} bean instance directly
 */
@Service
public class RedisService {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	public void setCache(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}
	
	public void setCache(String key, String value, long expires) {
		redisTemplate.opsForValue().set(key, value, expires, TimeUnit.SECONDS);
	}
	
	public void deleteCache(String key) {
		redisTemplate.delete(key);
	}
	
	public void deleteCache(Collection<String> keys) {
		redisTemplate.delete(keys);
	}
	
	public void increment(String key, int num) {
		ValueOperations<String, String> opsValue = redisTemplate.opsForValue();
		String last = opsValue.get(key);
		if (null == last) {
			opsValue.set(key, num + "");
		} else {
			int newNum = num + Integer.parseInt(last);
			opsValue.set(key, newNum + "");
		}
	}
	
	public void clearCache(String key) {
		redisTemplate.delete(key);
	}
	
	public boolean existKey(String key) {
		return redisTemplate.hasKey(key);
	}
	
	public String getCache (String key) {
		return redisTemplate.opsForValue().get(key);
	}
}
