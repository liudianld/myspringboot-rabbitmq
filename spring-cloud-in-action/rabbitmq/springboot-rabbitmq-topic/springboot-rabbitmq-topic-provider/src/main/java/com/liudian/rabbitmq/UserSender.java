package com.liudian.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.liudian.rabbitmq.config.RabbitmqConfig;

@Component
@EnableConfigurationProperties(RabbitmqConfig.class)
public class UserSender {
	
	@Autowired
	private AmqpTemplate rabbitmqTemplate;
	
	@Value("${rabbitmq.exchange}")
	private String exchange;
	
	public void send() throws Exception{
		this.rabbitmqTemplate.convertAndSend(this.exchange, "user.log.debug", "--------->user.log.debug");
		this.rabbitmqTemplate.convertAndSend(this.exchange, "user.log.info", "--------->user.log.info");
		this.rabbitmqTemplate.convertAndSend(this.exchange, "user.log.warn", "--------->user.log.warn");
		this.rabbitmqTemplate.convertAndSend(this.exchange, "user.log.error", "--------->user.log.error");
	}
}
