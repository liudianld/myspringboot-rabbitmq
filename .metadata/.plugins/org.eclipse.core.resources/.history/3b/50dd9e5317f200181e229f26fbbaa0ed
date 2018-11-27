package com.liudian.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Recevier {

	@RabbitListener(queues = "order-queue")
	public void process(String msg) {
		System.out.println("recevier: " + msg);
	}
}
