package com.liudian.rabbitmq;

import java.util.Map;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.liudian.rabbitmq.config.RabbitmqConfig;
import com.liudian.rabbitmq.entity.Order;
import com.rabbitmq.client.Channel;

@Component
@EnableConfigurationProperties(RabbitmqConfig.class)
@RabbitListener(bindings = @QueueBinding(
		value = @Queue(value = "${rabbitmq.queueOrder}", durable = "true"), 
		exchange = @Exchange(value = "${rabbitmq.exchange}", durable = "true", type = ExchangeTypes.TOPIC), 
		key = "order.*"))
public class OrderRecevier {

	@RabbitHandler
	public void process(@Payload Order order, Channel channel, @Headers Map<String, Object> headers) throws Exception {
		System.err.println("------------>消费端order: " + order.getId());
		/**
		 * Delivery Tag 用来标识信道中投递的消息。RabbitMQ 推送消息给 Consumer 时，会附带一个 Delivery Tag， 以便
		 * Consumer 可以在消息确认时告诉 RabbitMQ 到底是哪条消息被确认了。 RabbitMQ 保证在每个信道中，每条消息的 Delivery
		 * Tag 从 1 开始递增。
		 */
		Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

		/**
		 * multiple 取值为 false 时，表示通知 RabbitMQ 当前消息被确认 如果为 true，则额外将比第一个参数指定的 delivery
		 * tag 小的消息一并确认
		 */
		// 手工ACK
		channel.basicAck(deliveryTag, false);
	}
}
