package com.hzyice.springbootrabbitmq.sender;

/*
    work 工作模式
 */

import com.hzyice.springbootrabbitmq.constant.RabbitConstants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        rabbitTemplate.convertAndSend(RabbitConstants.TWORK_QUEUE, "heelo work MQ");
    }
}
