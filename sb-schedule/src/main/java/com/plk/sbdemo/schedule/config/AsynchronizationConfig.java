package com.plk.sbdemo.schedule.config;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * SpringBoot Asynchronous Feature Project
 * 开启异步支持
 * 实现异步执行方法
 */
@EnableAsync
@Configuration
public class AsynchronizationConfig implements AsyncConfigurer {

	@Value("${async.pool.core-size}")
	private int corePoolSize;

	@Value("${async.pool.max-size}")
	private int maxPoolSize;

	@Value("${async.queue.capacity}")
	private int queueCapacity;

	private static Logger logger = LoggerFactory.getLogger(AsynchronizationConfig.class);

	/**
	 * Customized Thread Pool
	 * 自定义线程池
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

	/*
	 * 异步方法异常处理
	 */
	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new AsyncUncaughtExceptionHandler() {
			@Override
			public void handleUncaughtException(Throwable ex, Method method, Object... params) {
				// Exception Deploy
				logger.error("Exception Thrown[method={}, msg={}]", method.getName(), ex.getMessage());
			}
		};
	}
}
