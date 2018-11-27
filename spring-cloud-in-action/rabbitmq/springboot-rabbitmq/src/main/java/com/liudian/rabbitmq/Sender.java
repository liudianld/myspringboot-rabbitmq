package com.liudian.rabbitmq;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public void send() {
		String msg = "hello world, lidian!" + new Date();
		this.amqpTemplate.convertAndSend("order-queue", msg);
	}
}
