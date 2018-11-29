package com.liudian.rabbitmq.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liudian.rabbitmq.constant.Constants;
import com.liudian.rabbitmq.entity.BrokerMessageLog;
import com.liudian.rabbitmq.entity.Order;
import com.liudian.rabbitmq.mapper.BrokerMessageLogMapper;
import com.liudian.rabbitmq.mapper.OrderMapper;
import com.liudian.rabbitmq.producer.RabbitmqOrderSender;
import com.liudian.rabbitmq.utils.DateUtils;
import com.liudian.rabbitmq.utils.FastJsonConvertUtil;

@Service
public class OrderService {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private BrokerMessageLogMapper brokerMessageLogMapper;

	@Autowired
	private RabbitmqOrderSender rabbitmqOrderSender;

	public void createOrder(Order order) throws Exception {
		// 使用当前时间当做订单创建时间（为了模拟一下简化）
		Date orderTime = new Date();
		// 插入业务数据
		orderMapper.insert(order);
		// 插入消息记录表数据
		BrokerMessageLog brokerMessageLog = new BrokerMessageLog();
		// 消息唯一ID
		brokerMessageLog.setMessageId(order.getMessageId());
		// 保存消息整体 转为JSON 格式存储入库
		brokerMessageLog.setMessage(FastJsonConvertUtil.convertObjectToJSON(order));
		// 设置消息状态为0 表示发送中
		brokerMessageLog.setStatus("0");
		// 设置消息未确认超时时间窗口为 一分钟
		brokerMessageLog.setNextRetry(DateUtils.addMinutes(orderTime, Constants.ORDER_TIMEOUT));
		brokerMessageLog.setCreateTime(new Date());
		brokerMessageLog.setUpdateTime(new Date());
		brokerMessageLogMapper.insertSelective(brokerMessageLog);
		// 发送消息
		rabbitmqOrderSender.sendOrder(order);
	}
}
