package com.liudian.rabbitmq;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.liudian.rabbitmq.config.RabbitmqConfig;

@Component
@EnableConfigurationProperties(RabbitmqConfig.class)
public class Sender {
	
	@Autowired
	private AmqpTemplate rabbitmqTemplate;
	
	@Value("${rabbitmq.exchange}")
	private String exchange;
	
	public void send() throws Exception{
		String msg = "hello world, lidian!" + new Date();
		this.rabbitmqTemplate.convertAndSend(this.exchange, "log.error.routing.key", msg);
	}
}
