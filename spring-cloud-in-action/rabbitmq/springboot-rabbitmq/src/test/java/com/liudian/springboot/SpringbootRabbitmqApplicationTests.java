package com.liudian.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.liudian.rabbitmq.Sender;
import com.liudian.rabbitmq.SpringbootRabbitmqApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringbootRabbitmqApplication.class)
public class SpringbootRabbitmqApplicationTests {

	@Autowired
	private Sender sender;
	
	@Test
	public void contextLoads() throws Exception {
		while (true) {
			Thread.sleep(3000);
			this.sender.send();
		}
	}

}
