package com.liudian.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.liudian.rabbitmq.OrderSender;
import com.liudian.rabbitmq.ProductSender;
import com.liudian.rabbitmq.RabbitmqDirectProviderApplication;
import com.liudian.rabbitmq.UserSender;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitmqDirectProviderApplication.class)
public class SpringbootRabbitmqApplicationTests {

	@Autowired
	private UserSender userSender;

	@Autowired
	private OrderSender orderSender;

	@Autowired
	private ProductSender productSender;

	@Test
	public void contextLoads() throws Exception {
		this.userSender.send();
		this.orderSender.send();
		this.productSender.send();
	}

}
