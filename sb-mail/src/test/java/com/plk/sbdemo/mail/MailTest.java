package com.plk.sbdemo.mail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.plk.sbdemo.mail.domain.MailTemplate;
import com.plk.sbdemo.mail.domain.NoticeUserBean;
import com.plk.sbdemo.mail.service.MailSendService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MailApplication.class)
public class MailTest {

	private static Logger logger = LoggerFactory.getLogger(MailTest.class);
	
	@Autowired
	private MailSendService mailService;
	
	@Before
	public void setup() {
		logger.info("Test Start");
	}
	
	//@Test
	public void sendTextMailTest() {
		mailService.sendTextMail("franplk@126.com", "Test For SpringBoot", "This Is A Test Mail");
	}
	
	/**
	 * Test For Attach
	 */
	//@Test
	public void sendAttachFileMail () {
		String filePath = "E:/images/earth.png";
		mailService.sendMailWithAttachFile("franplk@126.com", "Test For SpringBoot", "This Is A Test Mail", filePath);
	}
	
	/**
	 * Test For HTML And Template
	 */
	@Test
	public void sendTemplateMail () {
		MailTemplate<NoticeUserBean> tpl = new MailTemplate<NoticeUserBean>();
		tpl.setTplPath("emailTpl");
		tpl.setModelName("tplModel");
		tpl.setModelBean(new NoticeUserBean().setMailUser("HongTaiLang"));
		mailService.sendTemplateMail("franplk@126.com", "Test For SpringBoot", "franplk", tpl);
	}
	
	@After
	public void destroy() {
		
	}
}
