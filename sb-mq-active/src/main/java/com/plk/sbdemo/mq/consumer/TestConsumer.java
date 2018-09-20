package com.plk.sbdemo.mq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TestConsumer {

	@JmsListener(destination = "testAMQ")
	public void receiveQueue(String message) {
		System.err.println("收到消息--" + message);
	}
}
