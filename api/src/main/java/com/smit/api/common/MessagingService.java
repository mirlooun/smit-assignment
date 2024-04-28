package com.smit.api.common;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessagingService {
    @Autowired
    protected RabbitTemplate rabbitTemplate;

    protected void sendMessage(String queueName, Object object) {
        rabbitTemplate.convertAndSend(queueName, object);
    }

    protected void sendMessage(String exchange, String routingKey, Object object) {
        rabbitTemplate.convertAndSend(exchange, routingKey, object);
    }
}
