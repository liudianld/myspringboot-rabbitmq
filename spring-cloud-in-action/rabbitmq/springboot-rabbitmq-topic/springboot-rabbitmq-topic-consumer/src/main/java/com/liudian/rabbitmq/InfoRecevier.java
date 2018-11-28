package com.liudian.rabbitmq;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.liudian.rabbitmq.config.RabbitmqConfig;

@Component
@EnableConfigurationProperties(RabbitmqConfig.class)
@RabbitListener(
		bindings = @QueueBinding(
			value = @Queue(value = "${rabbitmq.queueInfo}", autoDelete="true"),
			exchange = @Exchange(value = "${rabbitmq.exchange}", type=ExchangeTypes.TOPIC),
			key = "*.log.info"))
public class InfoRecevier {

	@RabbitHandler
	public void process(String msg) {
		System.out.println("infoRecevier---------->" + msg);
	}
}
