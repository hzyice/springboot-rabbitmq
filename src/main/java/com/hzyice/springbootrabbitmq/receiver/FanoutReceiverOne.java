package com.hzyice.springbootrabbitmq.receiver;

import com.hzyice.springbootrabbitmq.constant.RabbitConstants;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 订阅（广播）模式消息发送
 * 监听队列 test.exchange.fanout
 */
@Component
@RabbitListener(queues = RabbitConstants.FANOUT_QUEUE_ONE)
public class FanoutReceiverOne {


    @RabbitHandler
    public void process(Object message) {
        System.out.println("1 FanoutReceiverOne 执行了...");
    }
}

