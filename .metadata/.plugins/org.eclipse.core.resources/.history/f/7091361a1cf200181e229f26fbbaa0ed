package com.liudian.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.liudian.rabbitmq.Sender;
import com.liudian.rabbitmq.RabbitmqDirectProviderApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitmqDirectProviderApplication.class)
public class SpringbootRabbitmqApplicationTests {

	@Autowired
	private Sender sender;

	@Test
	public void contextLoads() throws Exception {
		this.sender.send();
	}

}
