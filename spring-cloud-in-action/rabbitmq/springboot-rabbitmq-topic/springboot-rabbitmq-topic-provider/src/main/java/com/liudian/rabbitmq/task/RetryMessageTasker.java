package com.liudian.rabbitmq.task;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.liudian.rabbitmq.constant.Constants;
import com.liudian.rabbitmq.entity.BrokerMessageLog;
import com.liudian.rabbitmq.entity.Order;
import com.liudian.rabbitmq.exception.BusinessException;
import com.liudian.rabbitmq.mapper.BrokerMessageLogMapper;
import com.liudian.rabbitmq.producer.RabbitmqOrderSender;
import com.liudian.rabbitmq.utils.FastJsonConvertUtil;

@Component
public class RetryMessageTasker {

	@Autowired
	private RabbitmqOrderSender rabbitmqOrderSender;

	@Autowired
	private BrokerMessageLogMapper brokerMessageLogMapper;

	@Scheduled(initialDelay = 5000, fixedDelay = 10000)
	public void reSend() {
		System.out.println("--------------->开始定时任务");
		List<BrokerMessageLog> bList = brokerMessageLogMapper.query4StatusAndTimeoutMessage();
		bList.forEach(messageLog -> {
			if (messageLog.getTryCount() >= 3) {
				//如果重新尝试次数大于等于三次，我们就改变订单状态为失败
                brokerMessageLogMapper.changeBrokerMessageLogStatus(messageLog.getMessageId(), Constants.ORDER_SEND_FAILURE, new Date());
			} else {
				brokerMessageLogMapper.update4ReSend(messageLog.getMessageId(), new Date());
				Order reSendOrder = FastJsonConvertUtil.convertJSONToObject(messageLog.getMessage(), Order.class);
				try {
					rabbitmqOrderSender.sendOrder(reSendOrder);
				} catch (Exception e) {
					throw new BusinessException("1001", "订单重新发送失败！");
				}
			}
		});
	}
}
