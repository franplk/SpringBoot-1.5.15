package com.plk.sbdemo.async;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.plk.sbdemo.async.service.AsyncClassService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AsyncMethodTest {

	private static Logger logger = LoggerFactory.getLogger(AsyncMethodTest.class);
	
	@Autowired
	private AsyncClassService asyncService;
	
	@Before
	public void setup() {
		logger.info("-----Testing Start-----");
	}
	
	@Test
	public void doTest() throws Exception {
		
		logger.info("----Main Thread Start-----");
		
		asyncService.asyncMehthod();
		asyncService.asyncMehthodWithException();
		
		logger.info("----Main Thread End Without Waitting For Async Method-----");
		
		// 不要让主程序退出
		System.in.read();
	}
	
	@After
	public void complete() {
		logger.info("-----Testing End-----");
	}
}
