package com.plk.sbdemo.async.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Async Annotation Added To Method indicates that all methods perform Asynchronously
 */
@Async
@Service
public class AsyncClassService {

	private static Logger logger = LoggerFactory.getLogger(AsyncClassService.class);
	
	public void asyncMehthod() {
		logger.info("---Start To Perform Task---");
		doTask(5);
		logger.info("---Perform Task End---");
	}
	
	public void asyncMehthodWithException() {
		doTask(10);
		throw new RuntimeException("Perform Task Failed");
	}

	/**
	 * Task Cost Simulation
	 */
	private void doTask(int delay) {
		try {
			Thread.sleep(delay * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
