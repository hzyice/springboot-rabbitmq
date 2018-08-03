package com.hzyice.springbootrabbitmq.sender;

import com.hzyice.springbootrabbitmq.constant.RabbitConstants;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 通配符模式消息
 */
@Component
public class TopicSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;


    public void send() {
        String message = "this is topic model message.";
        // routing-key必须与绑定的routing-key匹配
        rabbitTemplate.convertAndSend(RabbitConstants.TOPIC_EXCHANGE, "test.topic.test.ww", message);
    }

    public void sendTwo() {
        String message = "this is topic model message two.";
        // routing-key必须与绑定的routing-key匹配
        rabbitTemplate.convertAndSend(RabbitConstants.TOPIC_EXCHANGE, "test.to.all.www", message);
    }
}
