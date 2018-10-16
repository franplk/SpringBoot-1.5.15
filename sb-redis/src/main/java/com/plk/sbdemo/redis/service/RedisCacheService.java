package com.plk.sbdemo.redis.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Use Mode-1: Using {@link RedisTemplate} bean instance directly
 */
@Service
public class RedisCacheService {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	public void setCache(String key, String value, long expires) {
		if (expires == 0) {
			redisTemplate.opsForValue().set(key, value);
		}
		redisTemplate.opsForValue().set(key, value, expires, TimeUnit.SECONDS);
	}
	
	public void deleteCache(String key) {
		redisTemplate.delete(key);
	}
	
	public boolean existKey(String key) {
		return redisTemplate.hasKey(key);
	}
	
	public String getCache (String key) {
		return redisTemplate.opsForValue().get(key);
	}
}
