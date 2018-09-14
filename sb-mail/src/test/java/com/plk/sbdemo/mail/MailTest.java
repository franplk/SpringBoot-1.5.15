package com.plk.sbdemo.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.plk.sbdemo.mail.service.MailSendService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MailApplication.class)
public class MailTest {

	@Autowired
	private MailSendService mailService;
	
	//@Test
	public void sendTextMailTest() {
		mailService.sendTextMail("xxx@163.com", "Test For SpringBoot", "This Is A Test Mail");
	}
	
	/**
	 * Test For Attach
	 */
	//@Test
	public void sendAttachFileMail () {
		String filePath = "E:/images/earth.png";
		mailService.sendMailWithAttachFile("xxx@163.com", "Test For SpringBoot", "This Is A Test Mail", filePath);
	}
	
	/**
	 * Test For HTML And Template
	 */
	@Test
	public void sendTemplateMail () {
		String tplPath = "emailTpl";
		mailService.sendTemplateMail(tplPath, "xxx@163.com", "Test For SpringBoot", "franplk");
	}
}
