package com.liudian.rabbitmq.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.liudian.rabbitmq.config.RabbitmqConfig;

//@Component
//@EnableConfigurationProperties(RabbitmqConfig.class)
public class OrderSender {
	
//	@Autowired
//	private AmqpTemplate rabbitmqTemplate;
//	
//	@Value("${rabbitmq.exchange}")
//	private String exchange;
//	
//	public void send() throws Exception{
//		this.rabbitmqTemplate.convertAndSend(this.exchange, "order.log.debug", "--------->order.log.debug");
//		this.rabbitmqTemplate.convertAndSend(this.exchange, "order.log.info", "--------->order.log.info");
//		this.rabbitmqTemplate.convertAndSend(this.exchange, "order.log.warn", "--------->order.log.warn");
//		this.rabbitmqTemplate.convertAndSend(this.exchange, "order.log.error", "--------->order.log.error");
//	}
}
