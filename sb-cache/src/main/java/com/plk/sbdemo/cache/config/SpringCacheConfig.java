package com.plk.sbdemo.cache.config;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Automatically Springboot uses {@code ConcurrenMapCacheManager} as the cache manager
 * when no third-party cache dependencies suche as redis or memecache.
 * Also we can customize {@code KeyGenerator} as a bean and then inject into Spring
 */
@Configuration
public class SpringCacheConfig {

	@Value("${cache.key.prefix}")
	private String prefix;
	
	/**
	 * Key Generator: Create A Cache Key According To The Class, Method And Parameters.
	 * Object Parameters Must Be Override {@code toString()} Method
	 */
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
}
