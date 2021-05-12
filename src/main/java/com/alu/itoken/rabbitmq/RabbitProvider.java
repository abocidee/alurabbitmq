package com.alu.itoken.rabbitmq;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitProvider {
     
	@Autowired
	private AmqpTemplate  amqpTemplate;
	
	public void send() {
		String content ="hello 收入凭boo" + new Date();
		
		System.out.println("provider " + content);
		amqpTemplate.convertAndSend("hello rabbitmq", content);
	}
}
