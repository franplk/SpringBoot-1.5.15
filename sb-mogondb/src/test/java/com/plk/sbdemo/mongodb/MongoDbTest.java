package com.plk.sbdemo.mongodb;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.plk.sbdemo.mongodb.entity.Article;
import com.plk.sbdemo.mongodb.service.ArticleService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MongodbApplication.class)
public class MongoDbTest {
	
	private Logger logger = LoggerFactory.getLogger(MongoDbTest.class);
	
	@Autowired
	private ArticleService articleService;

	@Before
	public void setUp() {
		logger.debug("------测试开始------");
	}
	
	@Test
	public void doTest() {
		// Save Document
		Article article = new Article(1L)
				.setAuthor("franplk")
				.setTitle("Test For MongoDB")
				.setSource("www")
				.setSummary("A Test")
				.setCreateDate(new Date())
				.setContent("SpringBoot Is Easy To Use");
		Article article_save = articleService.addArticle(article);
		Long id = article_save.getId();
		logger.info("Article Save Success With ID[{}]" , id);
		
		// Find Document
		Article findArticle = articleService.getById(id);
		logger.info("Get Article[id={}]", findArticle.getId());
	}
	
	@After
	public void destroy () {
		logger.debug("------测试结束------");
	}
}
