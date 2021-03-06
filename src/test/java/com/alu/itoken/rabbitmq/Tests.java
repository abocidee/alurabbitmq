package com.alu.itoken.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitmqApplication.class)
public class Tests {
   
	@Autowired
	RabbitProvider rabbitProvider;
	
	
	@Test
	public void send() {
		for(int i=0;i<100;i++) {
		  rabbitProvider.send();
		}
	}
}
