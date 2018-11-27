//package com.liudian.springboot;
//
//import java.util.UUID;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.liudian.springboot.entity.Order;
//import com.liudian.springboot.producer.OrderSender;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ApplicationTests {
//
//	@Test
//	public void contextLoads() {
//	}
//
//	@Autowired
//	private OrderSender orderSender;
//	
//	@Test
//	public void testSend1() throws Exception{
//		Order order = new Order();
//		order.setId("20181126000001");
//		order.setName("测试订单1");
//		order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID().toString());
//		orderSender.send(order);
//	}
//}
