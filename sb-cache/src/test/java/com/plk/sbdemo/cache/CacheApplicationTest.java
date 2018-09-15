package com.plk.sbdemo.cache;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.plk.sbdemo.cache.service.EntityService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CacheApplication.class)
public class CacheApplicationTest {

	private static Logger logger = LoggerFactory.getLogger(CacheApplicationTest.class);
	
	@Autowired
	private EntityService entityService;
	
	@Before
	public void setUp() {
		logger.info("开始测试---" + getClass().getSimpleName());
	}
	
	@Test
	public void doTest() {
		logger.info("Bean Info:{}", entityService.getBean().toString());
		logger.info("Bean Info:{}", entityService.getBean().toString());
		logger.info("Bean Info:{}", entityService.getBean().toString());
	}
	
	@After
	public void complete() {
		logger.info(getClass().getSimpleName() + "---测试完成");
	}
}
