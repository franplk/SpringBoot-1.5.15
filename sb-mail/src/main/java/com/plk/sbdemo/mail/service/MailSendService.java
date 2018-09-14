package com.plk.sbdemo.mail.service;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class MailSendService implements InitializingBean {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${mail.from.addr}")
	private String fromAddr;

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine templateEngine;

	/**
	 * 发送纯文本邮件
	 */
	public void sendTextMail(String toAddr, String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(fromAddr);
		message.setTo(toAddr);
		message.setSubject(subject);
		message.setText(content);
		try {
			mailSender.send(message);
			logger.info("简单邮件已经发送");
		} catch (Exception e) {
			logger.error("发送邮件时发生异常！", e);
		}
	}

	/**
	 * 发送HTML格式的邮件
	 */
	public void sendHtmlMail(String toAddr, String subject, String content) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			// true表示需要创建一个multipart message
			MimeMessageHelper mimeMsgHelper = new MimeMessageHelper(message, true);
			mimeMsgHelper.setFrom(fromAddr);
			mimeMsgHelper.setTo(toAddr);
			mimeMsgHelper.setSubject(subject);
			mimeMsgHelper.setText(content, true);
			mailSender.send(message);
			logger.info("html邮件发送成功");
		} catch (Exception e) {
			logger.error("发送html邮件时发生异常！", e);
		}
	}

	/**
	 * 发送带附件的邮件
	 */ 
	public void sendMailWithAttachFile(String toAddr, String subject, String content, String... filePaths) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			// 创建Mime消息
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(fromAddr);
			helper.setTo(toAddr);
			helper.setSubject(subject);
			helper.setText(content, true);
			
			// 添加附件
			for(String filePath : filePaths) {
				String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
				helper.addAttachment(fileName, new File(filePath));
			}
			
			mailSender.send(message);
			logger.info("带附件的邮件已经发送");
		} catch (Exception e) {
			logger.error("发送带附件的邮件时发生异常！", e);
		}
	}
	
	/**
	 * 发送模板邮件
	 */
	public void sendTemplateMail(String tplPath, String toAddr, String subject, String toUser) {
	    //创建邮件正文
	    Context context = new Context();
	    context.setVariable("mailUser", toUser);
	    String emailContent = templateEngine.process(tplPath, context);

	    sendHtmlMail(toAddr, subject ,emailContent);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(fromAddr, "Mail Sender Address Can not null!");
		Assert.notNull(templateEngine, "Mail TemplateEngine Can not null!");
	}
}
