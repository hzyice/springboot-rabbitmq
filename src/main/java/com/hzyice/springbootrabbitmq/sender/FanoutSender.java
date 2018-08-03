package com.hzyice.springbootrabbitmq.sender;

import com.hzyice.springbootrabbitmq.constant.RabbitConstants;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 订阅（广播）模式消息
 */
@Component
public class FanoutSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String message = "this is fanout model message.";
        // 不需要指定routing-key，任意
        rabbitTemplate.convertAndSend(RabbitConstants.FANOUT_EXCHANGE, "",  message);
    }
}
