package com.hzyice.springbootrabbitmq.sender;

import com.hzyice.springbootrabbitmq.constant.RabbitConstants;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 路由模式消息
 */
@Component
public class DirectSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String message = "this is direct model message.";
        // routing-key必须与绑定的routing-key一致
        rabbitTemplate.convertAndSend(RabbitConstants.DIRECT_EXCHANGE, "test.direct", message);

        //rabbitTemplate.convertAndSend(RabbitConstants.DIRECT_EXCHANGE, "test.directTwo", message);
    }


}
