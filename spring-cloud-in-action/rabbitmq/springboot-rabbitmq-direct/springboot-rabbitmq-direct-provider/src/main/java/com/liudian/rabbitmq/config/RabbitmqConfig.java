package com.liudian.rabbitmq.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix = "rabbitmq", ignoreUnknownFields = false)
@PropertySource(value = { "classpath:config/rebbitmq.properties" })
@Component
public class RabbitmqConfig {

	private String exchange;
	private String queueInfo;
	private String queueInfoRoutingKey;
	private String queueError;
	private String queueErrorRoutingKey;

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getQueueInfo() {
		return queueInfo;
	}

	public void setQueueInfo(String queueInfo) {
		this.queueInfo = queueInfo;
	}

	public String getQueueInfoRoutingKey() {
		return queueInfoRoutingKey;
	}

	public void setQueueInfoRoutingKey(String queueInfoRoutingKey) {
		this.queueInfoRoutingKey = queueInfoRoutingKey;
	}

	public String getQueueError() {
		return queueError;
	}

	public void setQueueError(String queueError) {
		this.queueError = queueError;
	}

	public String getQueueErrorRoutingKey() {
		return queueErrorRoutingKey;
	}

	public void setQueueErrorRoutingKey(String queueErrorRoutingKey) {
		this.queueErrorRoutingKey = queueErrorRoutingKey;
	}

}
