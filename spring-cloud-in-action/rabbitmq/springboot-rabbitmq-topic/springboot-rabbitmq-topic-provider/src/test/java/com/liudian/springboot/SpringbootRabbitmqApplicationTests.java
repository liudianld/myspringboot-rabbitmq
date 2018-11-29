package com.liudian.springboot;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.liudian.rabbitmq.RabbitmqDirectProviderApplication;
import com.liudian.rabbitmq.entity.Order;
import com.liudian.rabbitmq.services.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitmqDirectProviderApplication.class)
public class SpringbootRabbitmqApplicationTests {

	@Autowired
	private OrderService orderService;

	@Test
	public void testCreateOrder() throws Exception {
		Order order = new Order();
		order.setId(String.valueOf(System.currentTimeMillis()));
		order.setName("测试创建订单");
		order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID().toString());
		orderService.createOrder(order);
	}
}
