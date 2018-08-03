package com.hzyice.springbootrabbitmq.sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 消息确认机制
 */

@Component
public class AckSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void send(String exchange, String routingKey, Object message, String correlationDataId) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message, new CorrelationData(correlationDataId));
    }




}

