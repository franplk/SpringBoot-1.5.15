package com.plk.sbdemo.admin.service.email;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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

import com.plk.sbdemo.admin.domain.email.MailTemplate;

@Component
public class MailSendService implements InitializingBean {

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
		mailSender.send(message);
	}

	/**
	 * 发送HTML格式的邮件
	 * @throws MessagingException 
	 */
	public void sendHtmlMail(String toAddr, String subject, String content) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		// true表示需要创建一个multipart message
		MimeMessageHelper mimeMsgHelper = new MimeMessageHelper(message, true);
		mimeMsgHelper.setFrom(fromAddr);
		mimeMsgHelper.setTo(toAddr);
		mimeMsgHelper.setSubject(subject);
		mimeMsgHelper.setText(content, true);
		mailSender.send(message);
	}

	/**
	 * 发送带附件的邮件
	 * @throws MessagingException 
	 */ 
	public void sendMailWithAttachFile(String toAddr, String subject, String content, String... filePaths) throws MessagingException {
		// 创建Mime消息
		MimeMessage message = mailSender.createMimeMessage();
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
	}
	
	/**
	 * 发送模板邮件
	 * @throws MessagingException 
	 */
	public void sendTemplateMail(String toAddr, MailTemplate<?> template) throws MessagingException {
	    //创建邮件正文
	    Context context = new Context();
	    context.setVariable(template.getModelName(), template.getModelBean());
	    String emailContent = templateEngine.process(template.getTplPath(), context);

	    sendHtmlMail(toAddr, template.getSubject() ,emailContent);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(fromAddr, "Mail Sender Address Can not null!");
		Assert.notNull(templateEngine, "Mail TemplateEngine Can not null!");
	}
}
