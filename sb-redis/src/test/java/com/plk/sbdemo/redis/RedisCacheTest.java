package com.plk.sbdemo.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.plk.sbdemo.redis.service.RedisCacheService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisCacheTest {

	@Autowired
	private RedisCacheService cacheService;
	
	@Test
	public void cacheTest() throws Exception {
		String key = "developer.name";
		cacheService.setCache(key, "franplk", 10);
		//Thread.sleep(20000);//等待缓存过期
		if (cacheService.existKey(key)) {
			System.err.println(key + ":" + cacheService.getCache(key));
		} else {
			System.err.println(key + "缓存不存在了");
		}
	}
	
}
