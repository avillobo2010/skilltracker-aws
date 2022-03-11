package com.cognizant.skilltracker.producer;

import com.cognizant.skilltracker.data.FseDocument;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQSender {
	
	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Value("${cts.rabbitmq.exchange}")
	private String exchange;

	@Value("${cts.rabbitmq.routingkey}")
	private String routingkey;
	
	public void send(FseDocument document) {
		rabbitTemplate.convertAndSend(exchange, routingkey, document);
		System.out.println("Send msg = " + document);
	    
	}
}

