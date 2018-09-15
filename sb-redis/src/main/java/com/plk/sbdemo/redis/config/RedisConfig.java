package com.plk.sbdemo.redis.config;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Automatically Springboot will inject {@link RedisTemplate} instance when
 * {@literal spring-boot-starter-data-redis} included in project pom. And then
 * we can use it to build self-defined {@link CacheMananger} Also we can
 * customize {@link KeyGenerator} as a bean and then inject into Spring context.
 */
@Configuration
@EnableCaching
public class RedisConfig {

	@Value("${self.redis.prefix}")
	private String prefix;
	
	@Bean
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder(prefix);
				sb.append(target.getClass().getSimpleName());
				sb.append(method.getName());
				if (params != null && params.length > 0) {
					for (Object obj : params) {
						sb.append(obj.toString());
					}
				}
				return sb.toString();
			}
		};
	}

	@Bean
	public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
		RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
		// Set Default Cache TimeOut
		rcm.setDefaultExpiration(60);
		return rcm;
	}
}
