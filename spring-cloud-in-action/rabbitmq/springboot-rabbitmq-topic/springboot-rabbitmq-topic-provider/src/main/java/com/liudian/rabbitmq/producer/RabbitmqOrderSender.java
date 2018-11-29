package com.liudian.rabbitmq.producer;

import java.util.Date;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.liudian.rabbitmq.config.RabbitmqConfig;
import com.liudian.rabbitmq.constant.Constants;
import com.liudian.rabbitmq.entity.Order;
import com.liudian.rabbitmq.exception.BusinessException;
import com.liudian.rabbitmq.mapper.BrokerMessageLogMapper;

@Component
@EnableConfigurationProperties(RabbitmqConfig.class)
public class RabbitmqOrderSender {

	@Autowired
	private RabbitTemplate rabbitmqTemplate;

	@Value("${rabbitmq.exchange}")
	private String exchange;

	@Autowired
	private BrokerMessageLogMapper brokerMessageLogMapper;

	// 发送消息，构建自定义消息
	public void sendOrder(Order order) throws BusinessException {
		// 设置回调函数
		rabbitmqTemplate.setConfirmCallback(confirmCallback);
		// 消息唯一id
		CorrelationData correlationData = new CorrelationData(order.getMessageId());
		this.rabbitmqTemplate.convertAndSend(this.exchange, "order.abc", order, correlationData);
	}

	// 回调函数：confirm确认
	final ConfirmCallback confirmCallback = new ConfirmCallback() {

		@Override
		public void confirm(CorrelationData correlationData, boolean ack, String cause) {
			System.err.println("correlationData: " + correlationData);
			String messageId = correlationData.getId();
			System.err.println(ack);
			if (ack) {
				// 如果confirm返回成功，则更新
				brokerMessageLogMapper.changeBrokerMessageLogStatus(messageId, Constants.ORDER_SEND_SUCCESS, new Date());
			} else {
				// 如果失败则进行另外的操作
				System.err.println("changeBrokerMessageLogStatus的异常处理");
			}
		}
	};
}
