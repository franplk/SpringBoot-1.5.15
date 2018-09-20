package com.plk.sbdemo.mq.producer;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestProducer {

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

	@Scheduled(fixedDelay = 10000) // 每10s执行1次
	public void send() {
		Queue queue = new ActiveMQQueue("testAMQ");
		jmsMessagingTemplate.convertAndSend(queue, "hello,activeMQ");
		System.out.println("消息发送成功");
	}

}
