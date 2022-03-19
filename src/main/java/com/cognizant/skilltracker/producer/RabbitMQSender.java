package com.cognizant.skilltracker.producer;

import com.cognizant.skilltracker.model.FseSkillTracker;
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
	
	public void sendFseProfile(FseSkillTracker fseSkillTracker) {
		rabbitTemplate.convertAndSend(exchange, routingkey, fseSkillTracker);
		System.out.println("Sending Fse Profile = " + fseSkillTracker);
	    
	}


}

