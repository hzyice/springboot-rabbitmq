package com.hzyice.springbootrabbitmq.receiver;

import com.hzyice.springbootrabbitmq.constant.RabbitConstants;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 通配符模式消息接收
 * 监听队列 test.topic.queue
 */
@Component
@RabbitListener(queues = RabbitConstants.TOPIC_QUEUE)
public class TopicReceiver {

    private static Integer value = 1;

    @RabbitHandler
    public void process(Object message) {
        System.out.println("通配模式执行了" + value++ + "...");
    }
}

