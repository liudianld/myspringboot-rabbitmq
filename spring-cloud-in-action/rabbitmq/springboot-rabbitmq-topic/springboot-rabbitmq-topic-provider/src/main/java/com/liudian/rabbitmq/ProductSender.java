package com.liudian.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.liudian.rabbitmq.config.RabbitmqConfig;

@Component
@EnableConfigurationProperties(RabbitmqConfig.class)
public class ProductSender {
	
	@Autowired
	private AmqpTemplate rabbitmqTemplate;
	
	@Value("${rabbitmq.exchange}")
	private String exchange;
	
	public void send() throws Exception{
		this.rabbitmqTemplate.convertAndSend(this.exchange, "product.log.debug", "--------->product.log.debug");
		this.rabbitmqTemplate.convertAndSend(this.exchange, "product.log.info", "--------->product.log.info");
		this.rabbitmqTemplate.convertAndSend(this.exchange, "product.log.warn", "--------->product.log.warn");
		this.rabbitmqTemplate.convertAndSend(this.exchange, "product.log.error", "--------->product.log.error");
	}
}
