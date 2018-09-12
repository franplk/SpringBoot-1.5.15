package com.plk.sbdemo.schedule.config;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 开启异步支持
 */
@Configuration
@EnableAsync
public class AsynchronizationConfig {

	@Value("${async.pool.core-size}")
	private int corePoolSize = 10;
	
	@Value("${async.pool.max-size}")
	private int maxPoolSize = 200;
	
	@Value("${async.queue.capacity}")
	private int queueCapacity = 10;

	/**
	 * 异步线程池设置
	 */
	@Bean
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.initialize();
		return executor;
	}
}
